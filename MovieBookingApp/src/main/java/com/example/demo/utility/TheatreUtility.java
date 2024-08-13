package com.example.demo.utility;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Theatre;
import com.example.demo.request.TheatreRequest;
import com.example.demo.response.TheatreResponse;

public class TheatreUtility {
	
	public static TheatreResponse responsify(Theatre theatre) {
		TheatreResponse newResponse = new TheatreResponse();
		newResponse.setTheatreId(theatre.getTheatreId());
		newResponse.setTheatreName(theatre.getTheatreName());
		newResponse.setCity(theatre.getCity());
		newResponse.setTotalSeats(theatre.getTotalSeats());
		newResponse.setFullName(theatre.getTheatreName() + " , " + theatre.getCity());
		
		return newResponse;
	}
	
	public static List<TheatreResponse> responsifyAll(List<Theatre> theatres) {
		List<TheatreResponse> newResponse = new ArrayList<>();
		for ( Theatre theatre : theatres)
			newResponse.add(responsify(theatre));
		return newResponse;
	}
	
	public static Theatre requestToObj(TheatreRequest request) {
		Theatre theatre = new Theatre();
		theatre.setTheatreName(request.getTheatreName());
		theatre.setCity(request.getCity());
		theatre.setTotalSeats(request.getTotalSeats());
		return theatre;
	}
	
}