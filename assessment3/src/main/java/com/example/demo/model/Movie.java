package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name="movies")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String director;
	private String genre;
	private Integer releaseYear;
	private Double rating;
	
	
	
	public Movie() {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.rating = rating;
	}

	
	public final Integer getId() {
		return id;
	}
	
	public final void setId(Integer id) {
		this.id = id;
	}
	
	public final String getTitle() {
		return title;
	}
	
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getDirector() {
		return director;
	}
	
	public final void setDirector(String director) {
		this.director = director;
	}
	
	public final String getGenre() {
		return genre;
	}
	
	public final void setGenre(String genre) {
		this.genre = genre;
	}
	
	public final Integer getReleaseYear() {
		return releaseYear;
	}
	
	public final void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public final Double getRating() {
		return rating;
	}
	
	public final void setRating(Double rating) {
		this.rating = rating;
	}
	
	

}
