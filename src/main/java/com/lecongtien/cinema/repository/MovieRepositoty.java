package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepositoty extends JpaRepository<MovieEntity,Integer> {

}
