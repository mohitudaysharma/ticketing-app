package com.example.demo.utility;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Movie;
import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;

public class MovieUtility {
	
	public static MovieResponse toResponse(Movie movie) {
		MovieResponse response = new MovieResponse();
		response.setId(movie.getMovieId());
		response.setMovieCode(movie.getMovieCode());
		response.setMovieName(movie.getMovieName());
		response.setPoster(movie.getPoster());
		response.setDescription(movie.getDescription());
		return response;
	}
	
	public static List<MovieResponse> allToResponse(List<Movie> movies) {
		List<MovieResponse> resp = new ArrayList<>();
		for ( Movie m : movies )
			resp.add(toResponse(m));
		return resp;
	}
	
	public static Movie toMovieObj(MovieRequest movie, String Posterfile) {
		Movie newMovieObj = new Movie();
		newMovieObj.setMovieCode(movie.getMovieCode());
		newMovieObj.setMovieName(movie.getMovieName());
		newMovieObj.setDescription(movie.getDescription());
		newMovieObj.setPoster(Posterfile);
		return newMovieObj;
	}
}
