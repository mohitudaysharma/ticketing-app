package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;

public class MovieRequest {
	@NotBlank(message="Movie code must not be blank")
	private String movieCode;
	@NotBlank(message="Movie name must not be blank")
	private String movieName;
	private String description;
	
	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
