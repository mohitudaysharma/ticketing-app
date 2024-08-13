package com.example.demo.utility;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Show;
import com.example.demo.response.ShowResponse;

public class ShowUtility {
	
	public static ShowResponse toResponse(Show show) {
		ShowResponse response = new ShowResponse();
		response.setMovieId(show.getMovie().getMovieId());
		response.setShowId(show.getShowId());
		response.setMovieCode(show.getMovie().getMovieCode());
		response.setTheatreId(show.getTheatre().getTheatreId());
		response.setShowName(show.getMovie().getMovieCode()
				 + " + " + show.getTheatre().getTheatreName() + " , " + show.getTheatre().getCity());
		response.setTheatreName(show.getTheatre().getTheatreName() + " , " + show.getTheatre().getCity());
		response.setSeatsAvailable(show.getAvailable());
		return response;
	}
	
	public static List<ShowResponse> allToResponse(List<Show> shows) {
		List<ShowResponse> resp = new ArrayList<>();
		for ( Show s : shows )
			resp.add(toResponse(s));
		return resp;
	}
}
