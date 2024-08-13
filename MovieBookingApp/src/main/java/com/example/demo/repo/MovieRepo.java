package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, String> {
	
	Movie findByMovieId(String movieId);

	Movie findByMovieCode(String movieCode);
	
	void deleteByMovieCode(String movieCode);

}