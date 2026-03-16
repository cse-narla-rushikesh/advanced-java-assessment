package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.client.MovieClient;
import com.example.demo.model.Booking;
import com.example.demo.model.Movie;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private MovieClient movieClient;

    private List<Booking> bookings = new ArrayList<>();

    @PostMapping
    @CircuitBreaker(name="movieService", fallbackMethod="fallbackBooking")
    public Booking bookMovie(@RequestBody Booking booking) {

       // Movie movie = movieClient.getMovie(booking.getMovieId());
    	Movie movie = new Movie();
    	movie.setPrice(250);

        int total = movie.getPrice() * booking.getTickets();
        booking.setTotalAmount(total);

        bookings.add(booking);

        return booking;
    }
    
    public Booking fallbackBooking(Booking booking, Exception ex) {

        booking.setTotalAmount(0);
        return booking;

    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookings;
    }
}