package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.CinemaEntity;
import com.lecongtien.cinema.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity,Integer> {
    List<CinemaEntity> findByMaHtr(int maHtr);
    CinemaEntity findByIdCinema(int id);
    CinemaEntity findByNameCinema(String name);
}
