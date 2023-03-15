package com.lecongtien.cinema.service;

import com.lecongtien.cinema.entity.ShowTimeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShowtimeService {
    List<ShowTimeEntity> getAll();
    boolean deleteShowtimeById(int id);
    boolean updateShowtimeById(ShowTimeEntity showTimeEntity);
    boolean insertShowtime(ShowTimeEntity showTimeEntity);
}
