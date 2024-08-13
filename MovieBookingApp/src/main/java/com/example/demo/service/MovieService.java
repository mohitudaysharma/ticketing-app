package com.example.demo.service;

import java.util.List;

import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;

/**
 * @author mohit
 * Describes behaviour of the service layer for movies
 */
public interface MovieService {

	public List<MovieResponse> getAllMovies();
	
	public MovieResponse getMovie(String movieCode);
	
	public MovieResponse addMovie(MovieRequest movie, String posterFile);
	
	public String deleteMovie(String code);
	
	public MovieResponse editMovie(String code, MovieRequest movie);
}
