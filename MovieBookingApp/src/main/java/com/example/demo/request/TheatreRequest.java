package com.example.demo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class TheatreRequest {
	
	@NotBlank(message="Name is required")
	private String theatreName;
	
	@NotBlank(message="Theatres must have a city")
	private String city;
	
	@NotNull(message="Theatres must have a defined number of totalSeats")
	@Min(value = 5, message = "A minimum of 5 seats is needed to operate")
	private Integer totalSeats;

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

}