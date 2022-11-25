package com.lecongtien.cinema.service;

import com.lecongtien.cinema.entity.MovieEntity;
import com.lecongtien.cinema.repository.MovieRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    MovieRepositoty movieRepositoty;

    @Override
    public List<MovieEntity> getMovies() {
        return movieRepositoty.findAll();
    }
}
