package com.skillstorm.spring_data_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.spring_data_jpa.models.Director;
import com.skillstorm.spring_data_jpa.models.Movie;
import com.skillstorm.spring_data_jpa.repositories.MovieRepository;


@Service
public class MovieService {
    
    
    private final MovieRepository movieRepository;
    private final DirectorService directorService;

    public MovieService(MovieRepository movieRepository, DirectorService directorService) {
        this.movieRepository = movieRepository;
        this.directorService = directorService;
    }


    public List<Movie> findAllMovies() {
        return movieRepository.findAll();       // calls the findAll() method in JpaRepository
    }


    public Movie findMovieById(int id) {
        
        Optional<Movie> movie = movieRepository.findById(id);
        
        // if our database has a movie with a matching id, return it
        if(movie.isPresent()) {             // ispresent checks if the optional returned the object
            return movie.get();             // get will retrieve the object
        }

        //otherwise return null
        return null;
    }

    public List<Movie> findMoviesByRating(int rating) {
        
        Optional<List<Movie>> movies = movieRepository.findAllByRatingGreaterThanEqual(rating);

        if(movies.isPresent()) {
            return movies.get();
        }

        return null;
    }

    public Movie saveMovie(Movie movie) {

        /*
         * save performs an isNew() check using your primary key
         */


        Director directorWithId = directorService.saveDirector(movie.getDirector());
        movie.setDirector(directorWithId);
        return movieRepository.save(movie);                // comes OOTB with jpa respository
    }

    public int updateTitle(Movie movie, String newTitle){

        return movieRepository.updateMovieTitle(movie.getId(), newTitle);
    }

}