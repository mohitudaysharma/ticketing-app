package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.TicketRequest;
import com.example.demo.response.TicketResponse;
import com.example.demo.service.TicketService;
import com.example.demo.utility.CommonUtils;
import com.example.demo.utility.TokenUtility;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("ticket/v1")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	/**
	 * Get all Tickets, to add filters later(via path variables)
	 * @return list of tickets, ideally
	 */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTickets(@RequestHeader HttpHeaders header) {
		return new ResponseEntity<List<TicketResponse>>(ticketService.getAllTickets(TokenUtility.getUserId(header)), HttpStatus.OK);
	}
	
	/**
	 * Get a specific ticket
	 * Path variable: ticket code
	 * @return a ticket object
	 */
	@GetMapping("/get/{code}")
	public ResponseEntity<?> getTicket(@PathVariable String code) {
		return new ResponseEntity<TicketResponse>(ticketService.getTicket(code), HttpStatus.OK);
	}
	
	/**
	 * Add a ticket to DB
	 * 
	 * RequestBody: details of the ticket to be added
	 * @return successfully added data
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addTicket(@Valid @RequestBody TicketRequest ticket, @RequestHeader HttpHeaders header) {
		ticket.setUsername(TokenUtility.getUserId(header));
		return new ResponseEntity<TicketResponse>(ticketService.addTicket(ticket), HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{code}")
	public ResponseEntity<?> editTicket(@PathVariable String code, @RequestBody Map<String, Integer> booking, @RequestHeader HttpHeaders header) {
		return new ResponseEntity<TicketResponse>(ticketService.editTicket(code, booking.get("booked")), HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{code}")
	public ResponseEntity<?> deleteTicket(@PathVariable String code) {
		ticketService.deleteTicket(code);
		return new ResponseEntity<Map<String,String>>(CommonUtils.messageJson("The given ticket was deleted."), HttpStatus.OK);
	}

}