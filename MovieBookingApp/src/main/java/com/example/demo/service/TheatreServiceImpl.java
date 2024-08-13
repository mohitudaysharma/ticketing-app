package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Theatre;
import com.example.demo.exceptions.DataAlreadyExistsException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.NoDataException;
import com.example.demo.repo.TheatreRepo;
import com.example.demo.request.TheatreRequest;
import com.example.demo.response.TheatreResponse;
import com.example.demo.utility.TheatreUtility;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TheatreServiceImpl implements TheatreService {
	
	@Autowired
	public TheatreRepo theatreRepo;

	@Override
	public List<TheatreResponse> getAllTheatres() {
		List<Theatre> allTheatres= theatreRepo.findAll();
		if ( allTheatres == null || allTheatres.isEmpty())
			throw new NoDataException("No theatres found!");
		return TheatreUtility.responsifyAll(allTheatres);
	}

	@Override
	public TheatreResponse getTheatre(String id) {
		Theatre theatre = theatreRepo.findByTheatreId(id);
		if ( theatre == null ) 
			throw new DataNotFoundException("No such theatre! Please recheck id!");
		return TheatreUtility.responsify(theatre);
	}

	@Override
	public TheatreResponse addTheatre(TheatreRequest theatre) {
		Theatre alreadyExists = theatreRepo.findByNameAndCity(theatre.getTheatreName(), theatre.getCity());
		if ( alreadyExists != null ) 
			throw new DataAlreadyExistsException("Theatre with same details already exists!");
		return TheatreUtility.responsify(theatreRepo.saveAndFlush(TheatreUtility.requestToObj(theatre)));
	}

	@Override
	public void deleteTheatre(String id) {
		Theatre theatre = theatreRepo.findByTheatreId(id);
		if ( theatre == null ) 
			throw new DataNotFoundException("No such theatre! Please recheck id!");
		theatreRepo.deleteById(id);
	}

	@Override
	public TheatreResponse editTheatre(String id, TheatreRequest theatreData) {
		Theatre theatreExists = theatreRepo.findByTheatreId(id);
		if ( theatreExists == null ) 
			throw new DataNotFoundException("No such theatre! Please recheck id!");
		Theatre anotherTheatre =  theatreRepo.findByNameAndCity(theatreData.getTheatreName(), theatreData.getCity());
		if ( anotherTheatre != null && ! anotherTheatre.getTheatreId().equals(id)) {
			throw new DataAlreadyExistsException("Another theatre with same details already exists!");
		}
		theatreExists.setCity(Optional.ofNullable(theatreData.getCity()).orElse(theatreExists.getCity()));
		theatreExists.setTheatreName(Optional.ofNullable(theatreData.getTheatreName()).orElse(theatreExists.getTheatreName()));
		theatreExists.setTotalSeats(Optional.ofNullable(theatreData.getTotalSeats()).orElse(theatreExists.getTotalSeats()));
		return TheatreUtility.responsify(theatreRepo.saveAndFlush(theatreExists));
	}

}