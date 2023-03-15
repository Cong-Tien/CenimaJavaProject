package com.lecongtien.cinema.service;

import com.lecongtien.cinema.DTO.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoomService {
    List<RoomDTO> getTurnover();
    void clearAllCache();
}
