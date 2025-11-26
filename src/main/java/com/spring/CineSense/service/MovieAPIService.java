package com.spring.CineSense.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MovieAPIService {
   
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserSavedMovieRepository savedRepo;

    @Autowired
    private UserWatchedMovieRepository watchedRepo;

    @Autowired
    private MovieRatingRepository ratingRepo;

    private final String API_KEY = "fdae1aa2";
    private final String SEARCH_URL  = "https://www.omdbapi.com/?apikey=%s&s=%s";
    private final String DETAILS_URL = "https://www.omdbapi.com/?apikey=%s&i=%s&plot=full";

    public List<MovieDTO> searchMovies(String movieName) {

    	try {
		String url = String.format(SEARCH_URL, API_KEY, movieName);
        System.out.println("CALLING --> " + url);

        String raw;
        JsonNode json;

        
            raw = restTemplate.getForObject(url, String.class);
        
        ObjectMapper mapper=new ObjectMapper();
        
        json=mapper.readTree(raw);
        
        List<MovieDTO> movies = new ArrayList<>();
        
        if (json == null) {
            System.out.println("API returned NULL");
            return movies;   // return empty
        }
        
        if (json.has("Error")) {
            System.out.println("OMDB ERROR: " + json.get("Error").asText());
            return movies;   // return empty
        }

        if (json.has("Search")) {
            for (JsonNode movieNode : json.get("Search")) {
                MovieDTO movie = new MovieDTO();
                movie.setImdbId(movieNode.get("imdbID").asText());
                movie.setTitle(movieNode.get("Title").asText());
                movie.setYear(movieNode.get("Year").asText());
                movie.setPoster(movieNode.get("Poster").asText());
                movies.add(movie);
            }
        }
        
        return movies;
        
    	} catch (Exception e) {
            System.out.println("API ERROR: " + e.getMessage());
            return new ArrayList<>();
        }

    }
    
 // --------------------------------------------------------------------
    // FETCH MOVIE DETAILS & SAVE TO DB IF NEW
    // --------------------------------------------------------------------
    public MovieDTO getMovieDetails(String imdbId) {
        Movie movie = movieRepo.findByApiMovieId(imdbId);

        // If movie not in database, fetch from OMDB
        if (movie == null) {
            try {
                String url = String.format(DETAILS_URL, API_KEY, imdbId);
                String rawJson = restTemplate.getForObject(url, String.class);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(rawJson);

                movie = new Movie();
                movie.setApiMovieId(imdbId);
                movie.setTitle(json.get("Title").asText());
                movie.setReleaseYear(json.get("Year").asText());
                movie.setPosterUrl(json.get("Poster").asText());

                movie = movieRepo.save(movie);

            } catch (Exception e) {
                System.out.println("ERROR FETCHING MOVIE: " + e.getMessage());
                return null;
            }
        }

        // Convert to DTO
        MovieDTO dto = new MovieDTO();
        dto.setImdbId(movie.getApiMovieId());
        dto.setTitle(movie.getTitle());
        dto.setPoster(movie.getPosterUrl());
        dto.setYear(movie.getReleaseYear());
        return dto;
    }

    // --------------------------------------------------------------------
    // CHECK IF IN WISHLIST
    // --------------------------------------------------------------------
    public boolean isInWishlist(int userId, String imdbId) {
        return savedRepo.existsByUserUserIdAndMovieApiMovieId(userId, imdbId);
    }

    // --------------------------------------------------------------------
    // CHECK IF WATCHED
    // --------------------------------------------------------------------
    public boolean isWatched(int userId, String imdbId) {
        return watchedRepo.existsByUserUserIdAndMovieApiMovieId(userId, imdbId);
    }

    // --------------------------------------------------------------------
    // ADD TO WISHLIST
    // --------------------------------------------------------------------
    public void addToWishlist(int userId, String imdbId) {

        if (isInWishlist(userId, imdbId)) {
            return;
        }

        Movie movie = movieRepo.findByApiMovieId(imdbId);
        if (movie == null) {
            // Ensure movie exists in DB
            getMovieDetails(imdbId);
            movie = movieRepo.findByApiMovieId(imdbId);
        }

        User user = new User();
        user.setUserId(userId);

        UserSavedMovie save = new UserSavedMovie();
        save.setUser(user);
        save.setMovie(movie);
        save.setSavedAt(LocalDateTime.now());

        savedRepo.save(save);
    }

    // --------------------------------------------------------------------
    // MARK AS WATCHED
    // --------------------------------------------------------------------
    public void markWatched(int userId, String imdbId) {

        if (isWatched(userId, imdbId)) {
            return;
        }

        Movie movie = movieRepo.findByApiMovieId(imdbId);
        if (movie == null) {
            getMovieDetails(imdbId);
            movie = movieRepo.findByApiMovieId(imdbId);
        }

        User user = new User();
        user.setUserId(userId);

        UserWatchedMovie watch = new UserWatchedMovie();
        watch.setUser(user);
        watch.setMovie(movie);
        watch.setWatchedAt(LocalDateTime.now());

        watchedRepo.save(watch);
    }

    // --------------------------------------------------------------------
    // ADD REVIEW
    // --------------------------------------------------------------------
    public void addReview(int userId, String imdbId, int rating, String reviewText) {

        Movie movie = movieRepo.findByApiMovieId(imdbId);
        if (movie == null) {
            getMovieDetails(imdbId);
            movie = movieRepo.findByApiMovieId(imdbId);
        }

        User user = new User();
        user.setUserId(userId);

        MovieRating mr = new MovieRating();
        mr.setUser(user);
        mr.setMovie(movie);
        mr.setRating(rating);
        mr.setReviewText(reviewText);
        mr.setReviewDate(LocalDateTime.now());

        ratingRepo.save(mr);
    }
    
    public List<MovieDTO> getWishlistMovies(int userId) {
        List<UserSavedMovie> savedList = savedRepo.findByUserUserId(userId);
        List<MovieDTO> result = new ArrayList<>();

        for (UserSavedMovie saved : savedList) {
            Movie m = saved.getMovie();

            if (m == null) continue;

            MovieDTO dto = new MovieDTO();
            dto.setImdbId(m.getApiMovieId());
            dto.setTitle(m.getTitle());
            dto.setYear(m.getReleaseYear());
            dto.setPoster(m.getPosterUrl());

            result.add(dto);
        }

        return result;
    }

    public List<MovieDTO> getWatchedMovies(int userId) {
        List<UserWatchedMovie> watchedList = watchedRepo.findByUserUserId(userId);
        List<MovieDTO> result = new ArrayList<>();

        for (UserWatchedMovie wm : watchedList) {
            Movie m = wm.getMovie();

            if (m == null) continue;

            MovieDTO dto = new MovieDTO();
            dto.setImdbId(m.getApiMovieId());
            dto.setTitle(m.getTitle());
            dto.setYear(m.getReleaseYear());
            dto.setPoster(m.getPosterUrl());

            result.add(dto);
        }

        return result;
    }

    public List<MovieRating> getMyRatings(int userId) {
        return ratingRepo.findByUserUserId(userId);
    }
    
}
