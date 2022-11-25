package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.MovieRepositoty;
import com.lecongtien.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {
    @Autowired
   MovieService movieService;

    @Autowired
    MovieRepositoty movieRepositoty;
    @GetMapping("")
    public ResponseEntity<?> getMoviee(){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(movieService.getMovies());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
