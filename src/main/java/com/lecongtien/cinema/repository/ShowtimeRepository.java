package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.ShowTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowTimeEntity,Integer> {
    List<ShowTimeEntity> findByMaPhimAndIdCinema(int maPhim, int maCumRam);
    List<ShowTimeEntity> findByMaPhim(int maPhim);
    ShowTimeEntity findByIdShowtime(int id);
    List<ShowTimeEntity> findByIdRoom(int id);
}
