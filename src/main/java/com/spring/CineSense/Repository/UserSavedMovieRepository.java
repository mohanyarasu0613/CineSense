package com.spring.CineSense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.UserSavedMovie;

public interface UserSavedMovieRepository extends JpaRepository<UserSavedMovie, Integer>{

}
