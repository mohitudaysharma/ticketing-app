package com.example.demo.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TheatreResponse {

	@NotBlank(message="TheatreId is mandatory.")
	public String theatreId;
	
	@NotBlank(message="Name is required")
	public String theatreName;
	
	@NotBlank(message="Theatres must have a city")
	public String city;
	
	@NotNull(message="Theatres must have a defined number of totalSeats")
	public int totalSeats;
	
	public String fullName;

	public String getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(String theatreId) {
		this.theatreId = theatreId;
	}

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

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
