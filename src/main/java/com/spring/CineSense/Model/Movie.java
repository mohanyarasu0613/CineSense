package com.spring.CineSense.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Integer movieId;

	@Column(name = "api_movie_id", nullable = false, unique = true)
	private String apiMovieId;

	@Column(nullable = false)
	private String title;

	@Column(name = "release_year")
	private String releaseYear;

	@Column(name = "poster_url")
	private String posterUrl;

	// One Movie → Many saved records
	@OneToMany(mappedBy = "movie")
	private List<UserSavedMovie> savedByUsers;

	// One Movie → Many watched records
	@OneToMany(mappedBy = "movie")
	private List<UserWatchedMovie> watchedByUsers;

	// One Movie → Many ratings
	@OneToMany(mappedBy = "movie")
	private List<MovieRating> ratings;

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getApiMovieId() {
		return apiMovieId;
	}

	public void setApiMovieId(String apiMovieId) {
		this.apiMovieId = apiMovieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public List<UserSavedMovie> getSavedByUsers() {
		return savedByUsers;
	}

	public void setSavedByUsers(List<UserSavedMovie> savedByUsers) {
		this.savedByUsers = savedByUsers;
	}

	public List<UserWatchedMovie> getWatchedByUsers() {
		return watchedByUsers;
	}

	public void setWatchedByUsers(List<UserWatchedMovie> watchedByUsers) {
		this.watchedByUsers = watchedByUsers;
	}

	public List<MovieRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<MovieRating> ratings) {
		this.ratings = ratings;
	}

}
