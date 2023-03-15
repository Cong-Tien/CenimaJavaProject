package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.entity.RoomEntity;
import com.lecongtien.cinema.entity.ShowTimeEntity;
import com.lecongtien.cinema.entity.TicketEntity;
import com.lecongtien.cinema.payload.request.ShowtimeRequest;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.RoomRepository;
import com.lecongtien.cinema.repository.ShowtimeRepository;
import com.lecongtien.cinema.repository.TicketRepository;
import com.lecongtien.cinema.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/showtime")
public class ShowtimeController {
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowtimeService showtimeService;
    @GetMapping("")
    public ResponseEntity<?> getShowtime(){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(showtimeRepository.findAll());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> createShowtime(@RequestBody ShowtimeRequest request){
        ShowTimeEntity showTimeEntity = new ShowTimeEntity();
        showTimeEntity.setIdCinema(request.getMaRap());
        showTimeEntity.setShowtime(request.getNgayChieuGioChieu());
        showTimeEntity.setIdRoom(request.getMaPhong());
        showTimeEntity.setPrice(request.getGiaVe());
        showTimeEntity.setMaPhim(request.getMaPhim());

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(showtimeService.insertShowtime(showTimeEntity));
        List<ShowTimeEntity> show = showtimeRepository.findAll();

        RoomEntity room = roomRepository.findByIdRoom(request.getMaPhong());
        for(int i=0;i<room.getTotalSeat();i++){
            TicketEntity ticket = new TicketEntity();
            ticket.setLoaiGhe("Thuong");
            if(i<16){
                ticket.setGhe("A"+(i+1));
            }
            if(i>15 && i<32){
                ticket.setGhe("B"+(i+1-16));
            }
            if(i>31 && i<48){
                ticket.setGhe("C"+(i+1-32));
                ticket.setLoaiGhe("Vip");
            }
            if(i>47 && i <54){
                ticket.setGhe("D"+(i+1-48));
                ticket.setLoaiGhe("Vip");
            }
            if(i>53 && i <70){
                ticket.setGhe("E"+(i+1-54));
                ticket.setLoaiGhe("Vip");
            }
            if(i>69 && i <86){
                ticket.setGhe("F"+(i+1-70));
            }
            if(i>85 && i <102){
                ticket.setGhe("G"+(i+1-86));
            }
            if(i>101 && i <118){
                ticket.setGhe("H"+(i+1-102));
            }
            ticket.setDaDat(false);
            ticket.setGiaVe(request.getGiaVe());
            ticket.setIdShowtime(show.get(show.size()-1).getIdShowtime());
            ticket.setIdUser(0);
            ticketRepository.save(ticket);
        }
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
