package com.spring.CineSense.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.CineSense.DTO.MovieDTO;
import com.spring.CineSense.Model.MovieRating;
import com.spring.CineSense.Model.User;
import com.spring.CineSense.service.MovieAPIService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieAPIService movieService;

    // View movie details
    @GetMapping("/details")
    public String movieDetails(
            @RequestParam String movieId,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        MovieDTO movie = movieService.getMovieDetails(movieId);

        boolean inWishlist = movieService.isInWishlist(user.getUserId(), movieId);
        boolean watched = movieService.isWatched(user.getUserId(), movieId);

        model.addAttribute("movie", movie);
        model.addAttribute("inWishlist", inWishlist);
        model.addAttribute("watched", watched);

        return "movieDetails";
    }


    // Add to Wishlist
    @PostMapping("/addToWishlist")
    public String addToWishlist(
            @RequestParam String movieId,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        movieService.addToWishlist(user.getUserId(), movieId);

        return "redirect:/movie/details?movieId=" + movieId;
    }


    // Mark as Watched
    @PostMapping("/markWatched")
    public String markWatched(
            @RequestParam String movieId,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        movieService.markWatched(user.getUserId(), movieId);

        return "redirect:/movie/details?movieId=" + movieId;
    }


    // Submit Review
    @PostMapping("/review")
    public String review(
            @RequestParam String movieId,
            @RequestParam int rating,
            @RequestParam String reviewText,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        movieService.addReview(user.getUserId(), movieId, rating, reviewText);

        return "redirect:/movie/details?movieId=" + movieId;
    }
    
    @GetMapping("/wishlist")
    public String wishlist(HttpSession session, Model model) {
    	User user = (User) session.getAttribute("loggedInUser");
        List<MovieDTO> movies = movieService.getWishlistMovies(user.getUserId());
        model.addAttribute("wishlistMovies", movies);
		return "wishlist";
	}
    
    @GetMapping("/watched")
    public String watched(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        List<MovieDTO> movies = movieService.getWatchedMovies(user.getUserId());
        model.addAttribute("watchedMovies", movies);
    	return "watched";
    }
    
    @GetMapping("/myReviews")
    public String myReviews(HttpSession session, Model model) {

    	User user = (User) session.getAttribute("loggedInUser");
    	
        List<MovieRating> reviews = movieService.getMyRatings(user.getUserId());

        model.addAttribute("reviews", reviews);
        
    	return "myReviews";
    }

}