package com.spring.CineSense.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.UserSavedMovie;

public interface UserSavedMovieRepository extends JpaRepository<UserSavedMovie, Integer>{

    boolean existsByUserUserIdAndMovieApiMovieId(int userId, String apiMovieId);

	List<UserSavedMovie> findByUserUserId(int userId);
	
}
