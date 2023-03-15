package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.entity.*;
import com.lecongtien.cinema.payload.request.SignInRequest;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.payload.response.InforShowtime;
import com.lecongtien.cinema.payload.response.TicketResponse;
import com.lecongtien.cinema.repository.*;
import com.lecongtien.cinema.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieRepositoty movieRepositoty;
    @Autowired
    CinemaRepository cinemaRepository;
    @GetMapping("")
    public ResponseEntity<?> getTicketByShowtime(@RequestParam("maLC") int maLC){
        ShowTimeEntity showTime = showtimeRepository.findByIdShowtime(maLC);
        TicketResponse ticketResponse = new TicketResponse();
        RoomEntity room = roomRepository.findByIdRoom(showTime.getIdRoom());
        CinemaEntity cinema = cinemaRepository.findByIdCinema(showTime.getIdCinema());
        MovieEntity movie = movieRepositoty.findById(showTime.getMaPhim());
        InforShowtime inforShowtime = new InforShowtime();
        inforShowtime.setMaLichChieu(maLC);
        inforShowtime.setGioChieu(showTime.getShowtime());
        inforShowtime.setTenCumRap(cinema.getNameCinema());
        inforShowtime.setTenRap(room.getNameRoom());
        inforShowtime.setDiaChi(cinema.getInfor());
        inforShowtime.setHinhAnh(movie.getPoster());
        inforShowtime.setNgayChieu(showTime.getShowtime());
        ticketResponse.setThongTinPhim(inforShowtime);

        ticketResponse.setDanhSachGhe(ticketRepository.findByIdShowtime(maLC));
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(ticketResponse);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> bookingTicket(
           @PathVariable int id,
            @RequestBody List<TicketEntity> request
                                           ){
        for (TicketEntity ticket: request) {
            ticket.setDaDat(true);
            ticket.setIdUser(id);
            ticketRepository.save(ticket);
        }
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        //dataResponse.setData(userRepository.findByEmailAndPassword(request.getUsername(),request.getPassword()).get(0));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
