package com.spring.CineSense.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

// Many Users → One Role
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

// Relationships
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserSavedMovie> savedMovies;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserWatchedMovie> watchedMovies;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<MovieRating> ratings;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<UserSavedMovie> getSavedMovies() {
		return savedMovies;
	}

	public void setSavedMovies(List<UserSavedMovie> savedMovies) {
		this.savedMovies = savedMovies;
	}

	public List<UserWatchedMovie> getWatchedMovies() {
		return watchedMovies;
	}

	public void setWatchedMovies(List<UserWatchedMovie> watchedMovies) {
		this.watchedMovies = watchedMovies;
	}

	public List<MovieRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}

}
