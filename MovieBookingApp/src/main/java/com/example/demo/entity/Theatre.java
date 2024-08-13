package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Theatre {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String theatreId;
	
	private String theatreName;

	private String city;

	private int totalSeats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "theatre", orphanRemoval = true)
    private List<Show> shows;

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

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", city=" + city + ", totalSeats="
				+ totalSeats + " .";
	}
	
}
