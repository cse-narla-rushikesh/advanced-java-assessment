package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
