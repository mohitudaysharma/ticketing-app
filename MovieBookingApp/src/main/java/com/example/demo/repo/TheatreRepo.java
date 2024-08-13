package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, String>{

	public Theatre findByTheatreId(String theatreId);
	
	@Query("Select th from Theatre th where th.theatreName=?1 and th.city=?2")
	public Theatre findByNameAndCity(String name, String city);
	
}
