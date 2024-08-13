package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String ticketId;
	
	@ManyToOne(targetEntity=Show.class)
	@JoinColumn(name="show_id")
	private Show show;

	private String username;

	private int booked;
	private int available;

	@Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
	
	
    public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
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
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getAvailable() {
		return available;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
	
	public Show getShow() {
		return show;
	}
	
	public void setShow(Show show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", show=" + show + ", username=" + username + ", booked=" + booked
				+ ", available=" + available + ", createdAt=" + createdAt + "]";
	}

}