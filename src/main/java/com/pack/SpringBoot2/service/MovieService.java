package com.pack.SpringBoot2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.SpringBoot2.entity.Movie;
import com.pack.SpringBoot2.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepo;

	public Movie createMovie(Movie movie) {
		return movieRepo.save(movie);
	}
	
	public List<Movie> getAllMovies() {
		return movieRepo.findAll();
	}
	
	public Movie getMovieById(Integer id) {
		Optional opt=movieRepo.findById(id);
		if(opt.isPresent())
			return (Movie) opt.get();
		return null;
	}
}
