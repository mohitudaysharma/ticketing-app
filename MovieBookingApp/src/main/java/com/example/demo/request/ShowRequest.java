package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;

public class ShowRequest {

	@NotBlank
	private String movieId;
	
	@NotBlank
	private String theatreId;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}
}
