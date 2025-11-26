package com.spring.CineSense.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Integer>{

	boolean existsByUserUserIdAndMovieApiMovieId(int userId, String apiMovieId);

	Optional<MovieRating> findByUserUserIdAndMovieApiMovieId(int userId, String apiMovieId);

	List<MovieRating> findByUserUserId(int userId);
	
}
