package com.pack.SpringBoot2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pack.SpringBoot2.entity.Movie;
import com.pack.SpringBoot2.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestMovieRepository {

	@Autowired
	MovieRepository movieRepo;
	
	@Test
	void testCreateMovie() {
		Movie movie1=Movie.builder().id(1000).name("Friends").language("English").rating(5).type("comedy").build();
		Movie savedIntoDb=movieRepo.save(movie1);
		Movie movieFromDb=movieRepo.findById(savedIntoDb.getId()).get();
		assertEquals(savedIntoDb.getId(),movieFromDb.getId());
	}
	
	@Test
	void testGetAllMovie() {
		List<Movie> list=movieRepo.findAll();
		List<Movie> list1=new ArrayList<>();
		for(Movie m:list)
			list1.add(m);
		
		assertEquals(list.size(),list1.size());
	}
}
