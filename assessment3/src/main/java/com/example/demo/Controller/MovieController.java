package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Movie;
import com.example.demo.model.Service.MovieService;


@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Read - Display all movies
    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movie-list";
    }

    // Show form to add movie
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-form";
    }

    // Create Movie
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

    // Edit Movie
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "movie-form";
    }

    // Delete Movie
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}