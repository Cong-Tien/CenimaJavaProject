package com.lecongtien.cinema.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cum_rap")
@Data
public class CinemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCinema;
    @Column(name = "name_cinema")
    private String nameCinema;
    @Column(name = "logo_cinema")
    private String logoCinema;
    @Column(name = "infor")
    private String infor;
    @Column(name = "map_link")
    private String mapLink;
    @Column(name = "ngay_mo_cua")
    private Date ngayMoCua;
    @Column(name = "gio_mo_cua")
    private Date gioMoCua;
    @Column(name = "ma_htr")
    private int maHtr;

}
