package com.example.demo.service;

import java.util.List;

import com.example.demo.request.TicketRequest;
import com.example.demo.response.TicketResponse;

public interface TicketService {

	public List<TicketResponse> getAllTickets(String usrname);
	public TicketResponse getTicket(String id);
	public TicketResponse addTicket(TicketRequest tkt);
	public TicketResponse editTicket(String ticketId, int booked);
	public void deleteTicket(String ticketId);

}
