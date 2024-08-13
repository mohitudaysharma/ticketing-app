package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Show;
//import com.example.demo.entity.Movie;
//import com.example.demo.entity.Theatre;
import com.example.demo.entity.Ticket;

import jakarta.transaction.Transactional;

@Transactional
public interface TicketRepo extends JpaRepository<Ticket, String> {

	public Ticket findByTicketId(String ticketId);
	
	public List<Ticket> findByShow(Show show);
	
	@Query("select t from Ticket t where t.username=?1")
	public List<Ticket> findAllByUsername(String username);
}