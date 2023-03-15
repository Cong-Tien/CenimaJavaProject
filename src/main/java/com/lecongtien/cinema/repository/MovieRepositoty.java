package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepositoty extends JpaRepository<MovieEntity,Integer> {
    MovieEntity findById(int id);
    List<MovieEntity> findByTenPhimContaining(String ten);
}
