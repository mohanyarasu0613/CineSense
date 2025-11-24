package com.spring.CineSense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Integer>{

}
