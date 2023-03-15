package com.lecongtien.cinema.repository;

import com.lecongtien.cinema.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {
    List<TicketEntity> findByIdShowtime(int id);
    List<TicketEntity> findByIdUser(int id);
    List<TicketEntity> findByTrangThaiAndIdShowtimeAndIdUser(int trangThai,int id,int user);
    List<TicketEntity> findByTrangThaiAndIdShowtimeAndIdUserIsNot(int trangThai,int id,int user);
    List<TicketEntity> findByTrangThaiAndIdShowtime(int trangThai,int id);
    List<TicketEntity> findByIdUserAndIdShowtime(int user,int id);
    List<TicketEntity> findByIdShowtimeAndAndIdUserNot(int trangThai,int id);
    List<TicketEntity> findByTrangThaiAndIdUser(int trangThai,int id);
}
