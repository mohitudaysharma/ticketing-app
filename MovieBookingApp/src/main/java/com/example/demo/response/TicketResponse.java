package com.example.demo.response;

import java.time.LocalDateTime;

public class TicketResponse {

	private String ticketId;
	private String theatreName;
	private String movieCode;
	private String movieName;
	private String username;
	private int booked;
	private int total;
	private int available;
	private LocalDateTime createdAt;
	
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBooked() {
		return booked;
	}
	public void setBooked(int booked) {
		this.booked = booked;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public TicketResponse(String ticketId, String theatreName, String movieCode, String movieName, String username,
			int booked, int total, int available, LocalDateTime createdAt) {
		super();
		this.ticketId = ticketId;
		this.theatreName = theatreName;
		this.movieCode = movieCode;
		this.movieName = movieName;
		this.username = username;
		this.booked = booked;
		this.total = total;
		this.available = available;
		this.createdAt = createdAt;
	}
    
}
