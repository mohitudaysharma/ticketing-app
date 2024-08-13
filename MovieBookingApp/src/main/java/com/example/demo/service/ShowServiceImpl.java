package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Movie;
import com.example.demo.entity.Show;
import com.example.demo.entity.Theatre;
import com.example.demo.exceptions.DataAlreadyExistsException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.NoDataException;
import com.example.demo.repo.MovieRepo;
import com.example.demo.repo.ShowRepo;
import com.example.demo.repo.TheatreRepo;
import com.example.demo.request.ShowRequest;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private TheatreRepo theatreRepo;
	
	@Autowired
	private ShowRepo showRepo;

	@Override
	public List<Show> getAllShows() {
		List<Show> allShows =showRepo.findAll();
		if ( allShows == null || allShows.isEmpty() )
			throw new NoDataException("No shows found!");
		return allShows;
	}

	@Override
	public List<Show> getAllShowsForMovie(String movieId) {
		List<Show> allShows =showRepo.findByMovie(movieId);
		if ( allShows == null || allShows.isEmpty() )
			throw new NoDataException("No shows found!");
		return allShows;
	}
	
	@Override
	public List<Show> getAllShowsForTheatre(String theatreId) {
		List<Show> allShows =showRepo.findByTheatre(theatreId);
		if ( allShows == null || allShows.isEmpty() )
			throw new NoDataException("No shows found!");
		return allShows;
	}
	
	@Override
	public Show getShow(String showId) {
		Show show = showRepo.findByShowId(showId);
		if ( show == null )
			throw new DataNotFoundException("No show found for given Id!");
		return show;
	}

	@Override
	public Show addShow(ShowRequest req) {
		Show showValid = validateRequest(req);
		Show showFound = showRepo.getShowDetails(req.getMovieId(), req.getTheatreId());
		if ( showFound != null )
			throw new DataAlreadyExistsException("Show with given details exists!");
		return showRepo.saveAndFlush(showValid);
	}


	@Override
	public void deleteShow(String showId) {
		Show show = showRepo.findByShowId(showId);
		if ( show == null )
			throw new DataNotFoundException("No show found for given Id!");
		showRepo.delete(show);
	}
	
	public Show validateRequest(ShowRequest req) {
		Movie movie = movieRepo.findByMovieId(req.getMovieId());
		if ( movie == null )
			throw new DataNotFoundException("No such movieCode");
		Theatre theatre = theatreRepo.findByTheatreId(req.getTheatreId());
		if ( theatre == null )
			throw new DataNotFoundException("No such theatreId");
		return new Show(movie,theatre,theatre.getTotalSeats());
	}

}
