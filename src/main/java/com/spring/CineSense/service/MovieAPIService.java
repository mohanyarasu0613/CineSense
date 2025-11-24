package com.spring.CineSense.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.CineSense.Model.MovieDTO;

@Service
public class MovieAPIService {
   
    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY = "fdae1aa2";
    private final String API_URL = "https://www.omdbapi.com/?apikey=%s&s=%s";

    public List<MovieDTO> searchMovies(String movieName) {

    	try {
		String url = String.format(API_URL, API_KEY, movieName);
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
}
