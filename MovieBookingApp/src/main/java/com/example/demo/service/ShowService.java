package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Show;
import com.example.demo.request.ShowRequest;

public interface ShowService {
	
	public List<Show> getAllShows();
	
	public Show getShow(String showId);
	
	public Show addShow(ShowRequest show);
	
	public void deleteShow(String showId);
	
	public List<Show> getAllShowsForMovie(String movieId);
	
	public List<Show> getAllShowsForTheatre(String theatreId);
	
}