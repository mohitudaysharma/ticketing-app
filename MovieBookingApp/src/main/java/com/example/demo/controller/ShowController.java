package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Show;
import com.example.demo.request.ShowRequest;
import com.example.demo.response.ShowResponse;
import com.example.demo.service.ShowService;
import com.example.demo.utility.CommonUtils;
import com.example.demo.utility.ShowUtility;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("show/v1")
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	/**
	 * Get all shows
	 * @return list of shows
	 */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllShows(@RequestParam(name="movieId") Optional<String> movieIdParam,
			@RequestParam(name = "theatreId") Optional<String> theatreIdParam) {
		List<Show> allshows = new ArrayList<>();
		if ( movieIdParam.isPresent()) {
			allshows = showService.getAllShowsForMovie(movieIdParam.get());}
		else if ( theatreIdParam.isPresent()) {
			allshows = showService.getAllShowsForTheatre(theatreIdParam.get());}
		else {
			allshows = showService.getAllShows();}
		return new ResponseEntity<List<ShowResponse>>(ShowUtility.allToResponse(allshows), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getShow(@PathVariable String id) {
		return new ResponseEntity<ShowResponse>(ShowUtility.toResponse(showService.getShow(id)), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addShow(@Valid @RequestBody ShowRequest show) {
		return new ResponseEntity<ShowResponse>(ShowUtility.toResponse(showService.addShow(show)), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> deleteShow(@PathVariable String id) {
		showService.deleteShow(id);
		return new ResponseEntity<Map<String,String>>(CommonUtils.messageJson("The given show was deleted."), HttpStatus.OK);
	}
}
