package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.TheatreRequest;
import com.example.demo.response.TheatreResponse;
import com.example.demo.service.TheatreService;
import com.example.demo.utility.CommonUtils;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("theatre/v1")
public class TheatreController {
	
	@Autowired
	private TheatreService theatreService;
	
	/**
	 * Get all theatres
	 * @return list of theatres
	 */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTheatres() {
		return new ResponseEntity<List<TheatreResponse>>(theatreService.getAllTheatres(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTheatre(@PathVariable String id) {
		return new ResponseEntity<TheatreResponse>(theatreService.getTheatre(id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addTheatre(@Valid @RequestBody TheatreRequest theatre) {
		return new ResponseEntity<TheatreResponse>(theatreService.addTheatre(theatre), HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editTheatre(@PathVariable String id, @RequestBody TheatreRequest theatre) {
	return new ResponseEntity<TheatreResponse>(theatreService.editTheatre(id, theatre), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> deleteTheatre(@PathVariable String id) {
		theatreService.deleteTheatre(id);
		return new ResponseEntity<Map<String,String>>(CommonUtils.messageJson("The given theatre was deleted."), HttpStatus.OK);
	}
}
