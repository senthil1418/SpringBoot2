package com.pack.SpringBoot2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.SpringBoot2.entity.Movie;
import com.pack.SpringBoot2.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {
	
	@Autowired
	MovieService movieService;

	@PostMapping("/movie")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		try {
			var savedMovie=movieService.createMovie(movie);
			return new ResponseEntity<Movie>(savedMovie, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> getAllMovie() {
		 try {
			 List<Movie> list=movieService.getAllMovies();
			 if(list.isEmpty()) {
				 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			 }
			 return new ResponseEntity<>(list,HttpStatus.CREATED);
		 }
		 catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
	
	@GetMapping("/movie/{movid}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("movid")Integer id) {
		try {
			var movie=movieService.getMovieById(id);
			if(movie!=null) {
				return new ResponseEntity<>(movie,HttpStatus.OK);
			}
			else 
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
