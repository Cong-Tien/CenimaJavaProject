package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cinema")
public class CinemaControler {
    @Autowired
    CinemaRepository cinemaRepository;
    @GetMapping("")
    public ResponseEntity<?> getAllCinema(){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(cinemaRepository.findAll());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> getCinemaToSystem(@RequestParam("maHTR") int maHTR){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(cinemaRepository.findByMaHtr(maHTR));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
