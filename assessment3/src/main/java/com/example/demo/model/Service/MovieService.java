package com.example.demo.model.Service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MovieRepository;
import com.example.demo.model.Movie;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }
}
