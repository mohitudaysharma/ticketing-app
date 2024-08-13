package com.example.demo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TicketRequest {

	@NotBlank
	private String showId;
	
	@Min(1)
	private int booked;

	private String username;

	public TicketRequest(@NotBlank String showId, int booked, String username, int available) {
		super();
		this.showId = showId;
		this.booked = booked;
		this.username = username;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Total " + booked + " tickets booked by " + username + " for : " + showId + ".";
	}
	
}
