package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DataEvent;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Show;
import com.example.demo.entity.Theatre;
import com.example.demo.entity.Ticket;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.InvalidDataOperationException;
import com.example.demo.exceptions.NoDataException;
import com.example.demo.repo.ShowRepo;
import com.example.demo.repo.TicketRepo;
import com.example.demo.request.TicketRequest;
import com.example.demo.response.TicketResponse;


@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private ShowRepo showRepo;
	
	@Autowired
	private DataPublisherServiceImpl kafkaPublisher;

	@Override
	public List<TicketResponse> getAllTickets(String username) {
		List<Ticket> allTickets = ticketRepo.findAllByUsername(username);
		if ( allTickets == null || allTickets.isEmpty())
			throw new NoDataException("No tickets in DB!");
		kafkaPublisher.sendMessage(new DataEvent("GetAllTickets",allTickets.toString()));
		List<TicketResponse> allResponses = new ArrayList<>();
		for ( Ticket t : allTickets )
			allResponses.add(generateResponse(ticketRepo.findByTicketId(t.getTicketId())));
		return allResponses;
	}

	@Override
	public TicketResponse getTicket(String id) {
		Ticket tkt = ticketRepo.findByTicketId(id);
		if ( tkt == null )
			throw new DataNotFoundException("No such ticket! Please recheck ID!");
		kafkaPublisher.sendMessage(new DataEvent("GetATicket: "+id,tkt.toString()));
		return generateResponse(tkt);
	}

	@Override
	public TicketResponse addTicket(TicketRequest tkt) {
		Show showDetails = validateRequest(tkt);
		int newAvailable = showDetails.getAvailable() - tkt.getBooked();
		Ticket newTicket = ticketRepo.saveAndFlush(requestToTicket(tkt, newAvailable));
		showRepo.updateAvailableTickets(newAvailable, showDetails.getMovie().getMovieId(), showDetails.getTheatre().getTheatreId() );
		kafkaPublisher.sendMessage(new DataEvent("AddTicket",newTicket.toString()));
		return generateResponse(newTicket);
	}

	@Override
	public void deleteTicket(String tktId) {
		Ticket ticket = ticketRepo.findByTicketId(tktId);
		if ( ticket == null )
			throw new DataNotFoundException("No such ticket! Please recheck ID!");
		String movieId = ticket.getShow().getMovie().getMovieId();
		String theatreId = ticket.getShow().getTheatre().getTheatreId();
		int available =showRepo.getShowDetails(movieId, theatreId).getAvailable();
		ticketRepo.deleteById(tktId);
		kafkaPublisher.sendMessage(new DataEvent("DeleteATicket: "+tktId,ticket.toString()));
		showRepo.updateAvailableTickets(available+ticket.getBooked(), movieId, theatreId);
	}

	@Override
	public TicketResponse editTicket(String id, int booked) {
		Ticket ticket = ticketRepo.findByTicketId(id);
		if ( ticket == null )
			throw new DataNotFoundException("No such ticket! Please recheck ID!");
		Show showDetails = ticket.getShow();
		int newAvailable = showDetails.getAvailable() + ticket.getBooked() - booked;
		if ( newAvailable < 0 )
			throw new InvalidDataOperationException("Too many tickets added! Not enough seats available!");
		ticket.setAvailable(newAvailable);
		ticket.setBooked(booked);
		Ticket newTicket = ticketRepo.saveAndFlush(ticket);
		showRepo.updateAvailableTickets(newAvailable, showDetails.getMovie().getMovieId(), showDetails.getTheatre().getTheatreId() );
		kafkaPublisher.sendMessage(new DataEvent("EditATicket: "+id,newTicket.toString()));
		return generateResponse(newTicket);
	}
	
	public Ticket requestToTicket(TicketRequest req, int available) {
		Ticket tkt = new Ticket();
		tkt.setBooked(req.getBooked());
		tkt.setShow(showRepo.findByShowId(req.getShowId()));
		tkt.setUsername(req.getUsername());
		tkt.setAvailable(available);
		return tkt;
	}
	public TicketResponse generateResponse(Ticket tkt) {
		Show showDetails = tkt.getShow(); 
		Movie mv= showDetails.getMovie();
		Theatre th = showDetails.getTheatre();
		String theatreName = th.getTheatreName() + " , " + th.getCity();
		TicketResponse newResponse = new TicketResponse(tkt.getTicketId(),theatreName, mv.getMovieCode(),mv.getMovieName(),
				tkt.getUsername(),tkt.getBooked(),th.getTotalSeats(), tkt.getAvailable(), tkt.getCreatedAt());
		return newResponse;
	}
	
	public Show validateRequest(TicketRequest tkt) {
		Show showDetails = showRepo.findByShowId(tkt.getShowId());
		if ( showDetails == null )
			throw new InvalidDataOperationException("Show Id is invalid!");
		if ( showDetails.getAvailable() - tkt.getBooked() < 0 )
			throw new InvalidDataOperationException("Too many seats booked! Not enough available!");
		return showDetails;
	}

}