package com.pack.SpringBoot2;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.SpringBoot2.controller.MovieController;
import com.pack.SpringBoot2.entity.Movie;
import com.pack.SpringBoot2.service.MovieService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = MovieController.class)
class TestMovieController {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MovieService movieService;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testCreateMovie() throws Exception {
		Movie movie = Movie.builder().id(1000).name("Robert").language("Tamil").rating(4).type("action").build();
		given(movieService.createMovie(any(Movie.class))).willAnswer((inv) -> movie);
		ResultActions response = mockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(movie)));
		response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(movie.getName())))
				.andExpect(jsonPath("$.language", is(movie.getLanguage())));

	}

	@Test
	void testGetAllMovie() throws Exception {
		Movie movie1 = Movie.builder().id(1000).name("Robert").language("Tamil").rating(4).type("action").build();
		Movie movie2 = Movie.builder().id(1001).name("Lost World").language("English").rating(5).type("thriller")
				.build();

		List<Movie> list = new ArrayList<>();
		list.add(movie1);
		list.add(movie2);

		given(movieService.getAllMovies()).willReturn(list);

		ResultActions response = mockMvc.perform(get("/api/movie"));
		MockHttpServletResponse res = response.andReturn().getResponse();
		Movie[] mov = new ObjectMapper().readValue(res.getContentAsString(), Movie[].class);

		assertEquals("Robert", mov[0].getName());
		assertEquals("English", mov[1].getLanguage());
	}
	
	@Test
	void testGetMovieById() throws Exception {
		Movie movie1 = Movie.builder().id(1000).name("Robert").language("Tamil").rating(4).type("action").build();
		
		given(movieService.getMovieById(1000)).willReturn(movie1);
		
		ResultActions response=mockMvc.perform(get("/api/movie/{movid}",1000));
		MockHttpServletResponse res=response.andReturn().getResponse();
		Movie mov=new ObjectMapper().readValue(res.getContentAsString(), Movie.class);
		assertEquals("Tamil",mov.getLanguage());
	}

}
