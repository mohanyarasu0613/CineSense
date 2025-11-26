package com.spring.CineSense.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.UserWatchedMovie;

public interface UserWatchedMovieRepository extends JpaRepository<UserWatchedMovie, Integer>{

    boolean existsByUserUserIdAndMovieApiMovieId(int userId, String apiMovieId);

	List<UserWatchedMovie> findByUserUserId(int userId);
	
}
