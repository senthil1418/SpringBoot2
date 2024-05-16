package com.pack.SpringBoot2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pack.SpringBoot2.entity.Movie;
import com.pack.SpringBoot2.repository.MovieRepository;
import com.pack.SpringBoot2.service.MovieService;


@ExtendWith(MockitoExtension.class)
@SpringBootTest //load entire application context
class TestMovieService {

	@Autowired
	MovieService movieService;
	
	@MockBean
	MovieRepository movieRepo;
	
	@Test
	void testCreateMovie() {
		Movie movie1=Movie.builder().id(1000).name("ABCD").language("Hindi").rating(2).type("drama").build();
		when(movieRepo.save(movie1)).thenReturn(movie1);
		assertThat(movieService.createMovie(movie1)).isEqualTo(movie1);
	}
	
	@Test
	void testGetAllMovies() {
		Movie movie1=Movie.builder().id(1000).name("ABCD").language("Hindi").rating(2).type("drama").build();
		Movie movie2=Movie.builder().id(1001).name("Friends").language("English").rating(4).type("Comedy").build();
		List<Movie> list=new ArrayList<>();
		list.add(movie1);
		list.add(movie2);
		
		when(movieRepo.findAll()).thenReturn(list);
		assertThat(movieService.getAllMovies()).isEqualTo(list);
	}
	
	@Test
	void testGetMovieById() {
		Movie movie1=Movie.builder().id(1000).name("ABCD").language("Hindi").rating(2).type("drama").build();
		when(movieRepo.findById(1000)).thenReturn(Optional.of(movie1));
		assertThat(movieService.getMovieById(1000)).isEqualTo(movie1);
	}
}
