package com.skillstorm.spring_data_jpa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.spring_data_jpa.models.Movie;
import com.skillstorm.spring_data_jpa.services.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin("http://localhost:5173")
public class MovieController {
    
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies() {
        List<Movie> movies = movieService.findAllMovies();

        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id) {
        Movie movie = movieService.findMovieById(id);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }


    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Movie>> findMoviesByRating(@PathVariable int rating) {

        List<Movie> movies = movieService.findMoviesByRating(rating);

        if(movies == null) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

    }


    @PostMapping() 
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        
        // insert the director that is provided with the movie - handle this in the service
        Movie newMovie = movieService.saveMovie(movie);
        return new ResponseEntity<Movie>(newMovie, HttpStatus.CREATED);
    }


    @PutMapping() 
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
        
        Movie newMovie = movieService.saveMovie(movie);
        return new ResponseEntity<Movie>(newMovie, HttpStatus.OK);
    }

    @PutMapping("/title") 
    public ResponseEntity<Integer> updateMovieTitle(@RequestBody Movie movie, @RequestParam String newTitle) {
        
        int updated = movieService.updateTitle(movie, newTitle);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

}
