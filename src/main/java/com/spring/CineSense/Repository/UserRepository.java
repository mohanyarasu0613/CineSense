package com.spring.CineSense.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.CineSense.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
