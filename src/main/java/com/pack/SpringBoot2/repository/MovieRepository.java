package com.pack.SpringBoot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.SpringBoot2.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie,Integer>{

}
