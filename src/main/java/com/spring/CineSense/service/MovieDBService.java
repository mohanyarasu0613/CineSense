package com.spring.CineSense.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CineSense.DTO.MovieDTO;
import com.spring.CineSense.Model.Movie;
import com.spring.CineSense.Model.MovieRating;
import com.spring.CineSense.Model.User;
import com.spring.CineSense.Model.UserSavedMovie;
import com.spring.CineSense.Model.UserWatchedMovie;
import com.spring.CineSense.Repository.MovieRatingRepository;
import com.spring.CineSense.Repository.MovieRepository;
import com.spring.CineSense.Repository.UserSavedMovieRepository;
import com.spring.CineSense.Repository.UserWatchedMovieRepository;

@Service
public class MovieDBService {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserSavedMovieRepository savedRepo;

    @Autowired
    private UserWatchedMovieRepository watchedRepo;

    @Autowired
    private MovieRatingRepository ratingRepo;


    // ---------------------------------------------------------
    //  SAVE MOVIE IF NOT EXISTS
    // ---------------------------------------------------------
    private Movie saveMovieIfNotExists(MovieDTO dto) {

        Movie existing = movieRepo.findByApiMovieId(dto.getImdbId());

        if (existing != null) {
            return existing;
        }

        Movie movie = new Movie();
        movie.setApiMovieId(dto.getImdbId());
        movie.setTitle(dto.getTitle());
        movie.setPosterUrl(dto.getPoster());
        movie.setReleaseYear(dto.getYear());

        return movieRepo.save(movie);
    }


    // ---------------------------------------------------------
    //  CHECK IF MOVIE IS IN WISHLIST
    // ---------------------------------------------------------
    public boolean isInWishlist(Integer userId, String apiMovieId) {
        return savedRepo.existsByUserUserIdAndMovieApiMovieId(userId, apiMovieId);
    }


    // ---------------------------------------------------------
    //  CHECK IF MOVIE IS MARKED AS WATCHED
    // ---------------------------------------------------------
    public boolean isWatched(Integer userId, String apiMovieId) {
        return watchedRepo.existsByUserUserIdAndMovieApiMovieId(userId, apiMovieId);
    }


    // ---------------------------------------------------------
    //  ADD MOVIE TO WISHLIST
    // ---------------------------------------------------------
    public void addToWishlist(User user, MovieDTO dto) {

        Movie movie = saveMovieIfNotExists(dto);

        // prevent duplicate insert
        if (isInWishlist(user.getUserId(), dto.getImdbId())) {
            return;
        }

        UserSavedMovie saved = new UserSavedMovie();
        saved.setUser(user);
        saved.setMovie(movie);
        saved.setSavedAt(LocalDateTime.now());

        savedRepo.save(saved);
    }


    // ---------------------------------------------------------
    //  MARK MOVIE AS WATCHED
    // ---------------------------------------------------------
    public void markWatched(User user, MovieDTO dto) {

        Movie movie = saveMovieIfNotExists(dto);

        if (isWatched(user.getUserId(), dto.getImdbId())) {
            return;
        }

        UserWatchedMovie watched = new UserWatchedMovie();
        watched.setUser(user);
        watched.setMovie(movie);
        watched.setWatchedAt(LocalDateTime.now());

        watchedRepo.save(watched);
    }


    // ---------------------------------------------------------
    //  ADD OR UPDATE REVIEW
    // ---------------------------------------------------------
    public void addReview(User user, MovieDTO dto, Integer rating, String reviewText) {

        Movie movie = saveMovieIfNotExists(dto);

        MovieRating movieRating =
                ratingRepo.findByUserUserIdAndMovieApiMovieId(user.getUserId(), dto.getImdbId())
                        .orElse(new MovieRating());

        movieRating.setUser(user);
        movieRating.setMovie(movie);
        movieRating.setRating(rating);
        movieRating.setReviewText(reviewText);
        movieRating.setReviewDate(LocalDateTime.now());

        ratingRepo.save(movieRating);
    }

}
