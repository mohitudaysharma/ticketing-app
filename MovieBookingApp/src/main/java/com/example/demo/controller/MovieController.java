package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.request.MovieRequest;
import com.example.demo.response.MovieResponse;
import com.example.demo.service.MovieService;
import com.example.demo.utility.CommonUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("movie/v1")
@CrossOrigin("*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	/**
	 * Get all movies, to add filters later(via path variables)
	 * @return list of movies, ideally
	 */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllMovies() {
		return new ResponseEntity<List<MovieResponse>>(movieService.getAllMovies(), HttpStatus.OK);
	}
	
	/**
	 * Get a specific movie
	 * Path variable: movie code
	 * @return a movie object
	 */
	@GetMapping("/get/{code}")
	public ResponseEntity<?> getMovie(@PathVariable String code) {
		return new ResponseEntity<MovieResponse>(movieService.getMovie(code), HttpStatus.OK);
	}
	
	/**
	 * Add a movie to DB
	 * 
	 * RequestBody: details of the movie to be added
	 * @return successfully added data
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addMovie(@Valid @ModelAttribute MovieRequest movie, @RequestParam("poster") MultipartFile poster) {
		String filepath;
		try {
			if ( !poster.isEmpty())	{
				filepath= System.currentTimeMillis()+poster.getOriginalFilename();
				Path path = Paths.get("D:\\serveImg\\public\\",filepath);
				Files.copy(poster.getInputStream(), path);
			} else {
				filepath = "blank-photo-icon.jpg";
			}
		return new ResponseEntity<MovieResponse>(movieService.addMovie(movie,filepath), HttpStatus.CREATED);
		} catch ( IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<Map<String,String>>(CommonUtils.messageJson("Something went wrong with the request!"), HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/remove/{code}")
	public ResponseEntity<?> deleteMovie(@PathVariable String code) {
		String filename = movieService.deleteMovie(code);
//		File posterFile=null;
//		try {
//			String fullPath = Paths.get(uploadDirectory, filename).toString();
//			posterFile = new File(fullPath);
//			if(!posterFile.delete())
//				throw new Exception();
//		} catch (Exception e) {
//			return new ResponseEntity<String>("Some error in deleting poster!", HttpStatus.OK);
//		}
		return new ResponseEntity<Map<String,String>>(CommonUtils.messageJson("The given movie was deleted."), HttpStatus.OK);
	}
}
