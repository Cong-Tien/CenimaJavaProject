package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    List<RoomEntity> findByIdCinema(int idCenima);
    RoomEntity findByIdRoom(int idRoom);
}
