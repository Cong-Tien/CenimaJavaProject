package com.lecongtien.cinema.service;

import com.lecongtien.cinema.model.DTO.RoomDTO;

import java.util.List;


public interface RoomService {
    List<RoomDTO> getTurnover();
    void clearAllCache();
}
