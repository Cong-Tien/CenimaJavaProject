package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.DTO.RoomDTO;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.RoomRepository;
import com.lecongtien.cinema.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomService roomService;
    @GetMapping("")
    public ResponseEntity<?> getRoomByIdCinema(@RequestParam("maCumRap") int idCinema){
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(roomRepository.findByIdCinema(idCinema));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/clear-cache")
    public ResponseEntity<?> clearCacheRestaurant(){
        roomService.clearAllCache();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
    @GetMapping("/turnover")
    public ResponseEntity<?> getRoomByIdCinema(){
        List<RoomDTO> roomDTOList = roomService.getTurnover();
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(roomDTOList);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
