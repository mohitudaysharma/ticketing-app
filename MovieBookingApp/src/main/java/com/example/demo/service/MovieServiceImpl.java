package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DataEvent;
import com.example.demo.entity.Movie;
import com.example.demo.exceptions.DataAlreadyExistsException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.NoDataException;
import com.example.demo.repo.MovieRepo;
import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;
import com.example.demo.utility.MovieUtility;

import jakarta.transaction.Transactional;

/**
 * @author mohit
 * Implements MovieService behaviour
 * is the Service layer for Movie-side
 */

@Service
@Transactional
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private DataPublisherServiceImpl kafkaPublisher;

	@Override
	public List<MovieResponse> getAllMovies() throws NoDataException {
		List<Movie> movieList = movieRepo.findAll();
		if ( movieList == null || movieList.isEmpty() )
			throw new NoDataException("No movie records found!");
		kafkaPublisher.sendMessage(new DataEvent("GetAllMovies:",movieList.toString()));
		return MovieUtility.allToResponse(movieList);
	}

	@Override
	public MovieResponse getMovie(String code) {
		Movie foundMovie = movieRepo.findByMovieCode(code);
		if ( foundMovie == null ) 
			throw new DataNotFoundException("No such movie! Please recheck code!");
		kafkaPublisher.sendMessage(new DataEvent("GetAMovie: "+code,foundMovie.toString()));
		return MovieUtility.toResponse(foundMovie);
	}

	@Override
	public MovieResponse addMovie(MovieRequest movie,String posterFile) {
		if ( movieRepo.findByMovieCode(movie.getMovieCode()) != null)
			throw new DataAlreadyExistsException("Movie with this code alread exists!");
		Movie savedMovie = movieRepo.save(MovieUtility.toMovieObj(movie,posterFile));
		kafkaPublisher.sendMessage(new DataEvent("AddMovie:",savedMovie.toString()));
		return MovieUtility.toResponse(savedMovie);
	}

	@Override
	public String deleteMovie(String code) {
		Movie foundMovie = movieRepo.findByMovieCode(code);
		if ( foundMovie == null ) 
			throw new DataNotFoundException("No such movie! Please recheck code!");
		kafkaPublisher.sendMessage(new DataEvent("DeleteMovie: "+code,foundMovie.toString()));
		String filename = foundMovie.getPoster();
		movieRepo.deleteByMovieCode(code);
		return filename;
	}

	@Override
	public MovieResponse editMovie(String id, MovieRequest movie) {
		Movie foundMovie = movieRepo.findByMovieId(id);
		if ( foundMovie == null ) 
			throw new DataNotFoundException("No such movie! Please recheck Id!");
		Movie anotherMovie = movieRepo.findByMovieCode(movie.getMovieCode());
		if ( ! anotherMovie.getMovieId().equals(id) )
			throw new DataAlreadyExistsException("Another movie with this code alread exists!");
		foundMovie.setMovieCode(movie.getMovieCode());
		foundMovie.setMovieName(movie.getMovieName());
		kafkaPublisher.sendMessage(new DataEvent("EditMovie: "+id,foundMovie.toString()));
		return MovieUtility.toResponse(movieRepo.saveAndFlush(foundMovie));
	}

}
