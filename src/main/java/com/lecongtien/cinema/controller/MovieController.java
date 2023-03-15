package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.entity.CinemaEntity;
import com.lecongtien.cinema.entity.MovieEntity;
import com.lecongtien.cinema.entity.SystemEntity;
import com.lecongtien.cinema.payload.request.MovieRequest;
import com.lecongtien.cinema.payload.response.*;
import com.lecongtien.cinema.repository.CinemaRepository;
import com.lecongtien.cinema.repository.MovieRepositoty;
import com.lecongtien.cinema.repository.ShowtimeRepository;
import com.lecongtien.cinema.repository.SystemRepository;
import com.lecongtien.cinema.service.FileUploadServiceImp;
import com.lecongtien.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {
    @Autowired
   MovieService movieService;
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    SystemRepository systemRepository;
    @Autowired
    FileUploadServiceImp fileUploadService;
    @Autowired
    CinemaRepository cinemaRepository;
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
    @GetMapping("/name")
    public ResponseEntity<?> getMoviee(@RequestParam("tenPhim") String tenPhim){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(movieRepositoty.findByTenPhimContaining(tenPhim));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteMovie(@RequestParam("idMovie") int id){
        movieRepositoty.deleteById(id);
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xoa thanh cong");
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/get-movie-edit")
    public ResponseEntity<?> getMovieEdit(@RequestParam("idMovie") int id){
        System.out.println(id);
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xoa thanh cong");
        dataResponse.setData(movieRepositoty.findById(id));
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping(value = "/edit",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateMovie(@ModelAttribute MovieRequest movieRequest){
        //System.out.println("tenPhim " + movieRequest.getHinhAnh().getOriginalFilename());
        //System.out.println("tenPhim " + movieRequest.getTenPhim());
        System.out.println("Check "+  movieRequest.getMaPhim());
        fileUploadService.storeFile(movieRequest.getHinhAnh());
        MovieEntity movie = new MovieEntity();
        movie.setId(movieRequest.getMaPhim());
        movie.setDanhGia(movieRequest.getDanhGia());
        movie.setMoTa(movieRequest.getMoTa());
        movie.setHot(movieRequest.isHot());
        movie.setDaoDien("Châu Tinh Trì");
        if(movieRequest.getHinhAnh() != null){
            movie.setPoster(movieRequest.getHinhAnh().getOriginalFilename());
        }
        else {
            movie.setPoster(movieRepositoty.findById(movieRequest.getMaPhim()).getPoster());
        }
        movie.setNamSX(2021);
        movie.setNgayKhoiChieu(movieRequest.getNgayKhoiChieu());
        movie.setNgayKetThuc("20/12/2022");
        movie.setTenPhim(movieRequest.getTenPhim());
        movie.setDangChieu(movieRequest.isDangChieu());
        movie.setSapChieu(movieRequest.isSapChieu());
        movie.setThoiLuong(120);
        movie.setSanXuat("Hutech University");
        movie.setTrailer(movieRequest.getTrailer());

        movieRepositoty.save(movie);

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping(value = "/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> createMovie(@ModelAttribute MovieRequest movieRequest){
        //System.out.println("tenPhim " + movieRequest.getHinhAnh().getOriginalFilename());
        //System.out.println("tenPhim " + movieRequest.getTenPhim());
        fileUploadService.storeFile(movieRequest.getHinhAnh());
        MovieEntity movie = new MovieEntity();
        movie.setDanhGia(movieRequest.getDanhGia());
        movie.setMoTa(movieRequest.getMoTa());
        movie.setHot(movieRequest.isHot());
        movie.setDaoDien("Châu Tinh Trì");
        movie.setPoster(movieRequest.getHinhAnh().getOriginalFilename());
        movie.setNamSX(2021);
        movie.setNgayKhoiChieu(movieRequest.getNgayKhoiChieu());
        movie.setNgayKetThuc("20/12/2022");
        movie.setTenPhim(movieRequest.getTenPhim());
        movie.setThoiLuong(120);
        movie.setSanXuat("Hutech University");
        movie.setTrailer(movieRequest.getTrailer());
        movie.setDangChieu(movieRequest.isDangChieu());
        movie.setSapChieu(movieRequest.isSapChieu());

        movieRepositoty.save(movie);

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getMovieDetail(@RequestParam("idMovie") int idMovie){
        MovieDetailShowtime movieDetailShowtime = new MovieDetailShowtime();
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        MovieEntity movie = movieRepositoty.findById(idMovie);
        movieDetailShowtime.setId(movie.getId());
        movieDetailShowtime.setTenPhim(movie.getTenPhim());
        movieDetailShowtime.setTrailer(movie.getTrailer());
        movieDetailShowtime.setPoster(movie.getPoster());
        movieDetailShowtime.setDanhGia(movie.getDanhGia());
        movieDetailShowtime.setHot(movie.getHot());
        movieDetailShowtime.setDaoDien(movie.getDaoDien());
        movieDetailShowtime.setNgayKhoiChieu(movie.getNgayKhoiChieu());


        List<DataListSystem> data = new ArrayList<>();

        List<DataShowTimeAccordingToMovie> dataMovie = new ArrayList<>();
        List<SystemEntity> listSystem = systemRepository.findAll();
        for (SystemEntity system:listSystem) {
            List<DataListCinema> dataCinema = new ArrayList<>();
            DataListSystem dataListSystem = new DataListSystem();
            dataListSystem.setMaHeThongRap(system.getMaHtr());
            dataListSystem.setTenHeThongRap(system.getTenHtr());
            dataListSystem.setLogo(system.getLogo());
            for (CinemaEntity cinema: cinemaRepository.findByMaHtr(system.getMaHtr())) {
                DataListCinema list = new DataListCinema();
                list.setMaCumRap(cinema.getIdCinema());
                list.setHinhAnh(cinema.getLogoCinema());
                list.setTenCumRap(cinema.getNameCinema());
                list.setDiaChi(cinema.getInfor());

                list.setDanhSachPhim(showtimeRepository.findByMaPhimAndIdCinema(idMovie,cinema.getIdCinema()));
                dataCinema.add(list);
            }
            dataListSystem.setListCumrap(dataCinema);
            data.add(dataListSystem);
        }
        movieDetailShowtime.setHeThongRapChieu(data);
        dataResponse.setData(movieDetailShowtime);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
