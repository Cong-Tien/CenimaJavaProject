package com.lecongtien.cinema.entity;

import javax.persistence.*;

@Entity(name = "show_time")
public class ShowTimeEntity {
//    id_showtime int auto_increment,
//    showtime DATETIME NOT NULL,
//    price int NOT NULL,
//    status INT NOT NULL DEFAULT '0', -- 0: Chưa tạo vé cho lịch chiếu || 1: Đã tạo vé
//
//    id_room int,
//    id_cinema int,
//    ma_phim int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idShowtime;
    @Column(name = "showtime")
    private String showtime;
    @Column(name = "price")
    private int price;
    @Column(name = "status")
    private int status;
    @Column(name = "id_room")
    private int idRoom;
    @Column(name = "id_cinema")
    private int idCinema;
    @Column(name = "ma_phim")
    private int maPhim;

    public int getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(int idShowtime) {
        this.idShowtime = idShowtime;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }
}
