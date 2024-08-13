package com.example.demo.service;

import java.util.List;

import com.example.demo.request.TheatreRequest;
import com.example.demo.response.TheatreResponse;

public interface TheatreService {

	public List<TheatreResponse> getAllTheatres();
	
	public TheatreResponse getTheatre(String id);
	
	public TheatreResponse addTheatre(TheatreRequest theatre);
	
	public TheatreResponse editTheatre(String id, TheatreRequest theatre);
	
	public void deleteTheatre(String id);
	
}
