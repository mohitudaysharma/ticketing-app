package com.example.demo.response;

import jakarta.validation.constraints.NotBlank;

public class MovieResponse {

	private String id;

	@NotBlank(message="Movie code must not be blank")
	private String movieCode;
	@NotBlank(message="Movie name must not be blank")
	private String movieName;
	private String poster;
	private String description;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}