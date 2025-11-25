package com.spring.CineSense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
		
	Movie findByApiMovieId(String apiMovieId);
	
}
