package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="show_table")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String showId;
	
	@ManyToOne(targetEntity=Movie.class)
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	@ManyToOne(targetEntity=Theatre.class)
	@JoinColumn(name="theatre_id")
	private Theatre theatre;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "show",orphanRemoval = true)
    private List<Ticket> tickets;
	
	private int available;

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	public Show() { super(); }

	public Show( Movie movie, Theatre theatre, int available) {
		super();
		this.movie = movie;
		this.theatre = theatre;
		this.available = available;
	}

	@Override
	public String toString() {
		return "Show [showId=" + showId + ", movie=" + movie + ", theatre=" + theatre
				+ ", available=" + available + "]";
	}
	
}