package com.lecongtien.cinema.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lecongtien.cinema.entity.CinemaEntity;
import com.lecongtien.cinema.entity.MovieEntity;
import com.lecongtien.cinema.entity.SystemEntity;
import com.lecongtien.cinema.payload.response.DataListCinema;
import com.lecongtien.cinema.payload.response.DataListSystem;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.payload.response.DataShowTimeAccordingToMovie;
import com.lecongtien.cinema.repository.CinemaRepository;
import com.lecongtien.cinema.repository.MovieRepositoty;
import com.lecongtien.cinema.repository.ShowtimeRepository;
import com.lecongtien.cinema.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemRepository systemRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MovieRepositoty movieRepositoty;
    @Autowired
    ShowtimeRepository showtimeRepository;
    @GetMapping("")
    public ResponseEntity<?> getSysytem(){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(systemRepository.findAll());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/systemShowtime")
    public ResponseEntity<?> getSysytemShowtime(){
        String key = "dataSystem";
        Gson gson = new Gson();

        DataResponse dataResponse= new DataResponse();
        List<DataListSystem> data = new ArrayList<>();
        if(redisTemplate.hasKey(key)) {

            // key có  tồn tại
            String dataRedis = (String) redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            List<DataListSystem> listSystem = null;
            try {
                listSystem = mapper.readValue(dataRedis , List.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            data = listSystem;
            System.out.println("cos cache");
        }
        else{
            List<SystemEntity> listSystem = systemRepository.findAll();
            for (SystemEntity system:listSystem) {
                DataListSystem dataListSystem = new DataListSystem();
                dataListSystem.setMaHeThongRap(system.getMaHtr());
                dataListSystem.setTenHeThongRap(system.getTenHtr());
                dataListSystem.setLogo(system.getLogo());
                List<DataListCinema> dataCinema = new ArrayList<>();
                for (CinemaEntity cinema: cinemaRepository.findByMaHtr(system.getMaHtr())) {
                    DataListCinema list = new DataListCinema();
                    list.setMaCumRap(cinema.getIdCinema());
                    list.setHinhAnh(cinema.getLogoCinema());
                    list.setTenCumRap(cinema.getNameCinema());
                    list.setDiaChi(cinema.getInfor());
                    List<DataShowTimeAccordingToMovie> dataMovie = new ArrayList<>();
                    for (MovieEntity movie: movieRepositoty.findAll()) {
                        DataShowTimeAccordingToMovie movieShowtime = new DataShowTimeAccordingToMovie();
                        movieShowtime.setMaPhim(movie.getId());
                        movieShowtime.setHot(movie.getHot());
                        movieShowtime.setTenPhim(movie.getTenPhim());
                        movieShowtime.setHinhAnh(movie.getPoster());
                        movieShowtime.setListShowTime(showtimeRepository.findByMaPhimAndIdCinema(movie.getId(),cinema.getIdCinema()));
                        dataMovie.add(movieShowtime);
                    }
                    list.setDanhSachPhim(dataMovie);
                    dataCinema.add(list);
                }
                dataListSystem.setListCumrap(dataCinema);
                data.add(dataListSystem);
                String json = gson.toJson(data);
                redisTemplate.opsForValue().set(key,json);
            }
            System.out.println("ko cache");
        }
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(data);
        System.out.println(cinemaRepository.findByMaHtr(1).size());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
