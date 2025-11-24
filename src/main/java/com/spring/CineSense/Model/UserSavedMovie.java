package com.spring.CineSense.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user_saved_movies", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "movie_id" }))
public class UserSavedMovie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Many Saved → One User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// Many Saved → One Movie
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@Column(name = "saved_at")
	private LocalDateTime savedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(LocalDateTime savedAt) {
		this.savedAt = savedAt;
	}

}
