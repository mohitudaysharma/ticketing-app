package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Show;

import jakarta.transaction.Transactional;

@Transactional
public interface ShowRepo extends JpaRepository<Show, String> {
	
	@Query("select s from Show s where movie.movieId=?1 and theatre.theatreId=?2")
	public Show getShowDetails(String movieId, String theatreId);

	@Modifying
	@Query("update Show s set s.available=:available where s.movie.movieId=:movieId and s.theatre.theatreId=:theatreId")
	public void updateAvailableTickets(int available, String movieId, String theatreId);

	public Show findByShowId(String showId);
	
	@Query("select s from Show s where movie.movieId=?1")
	public List<Show> findByMovie(String theatreId);
	
	@Query("select s from Show s where theatre.theatreId=?1")
	public List<Show> findByTheatre(String theatreId);

}