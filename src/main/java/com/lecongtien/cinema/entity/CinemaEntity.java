package com.lecongtien.cinema.entity;

import javax.persistence.*;

@Entity(name = "cum_rap")
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
    @Column(name = "gio_mo_cua")
    private String gioMoCua;
    @Column(name = "ma_htr")
    private int maHtr;

    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    public String getLogoCinema() {
        return logoCinema;
    }

    public void setLogoCinema(String logoCinema) {
        this.logoCinema = logoCinema;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getGioMoCua() {
        return gioMoCua;
    }

    public void setGioMoCua(String gioMoCua) {
        this.gioMoCua = gioMoCua;
    }

    public int getMaHtr() {
        return maHtr;
    }

    public void setMaHtr(int maHtr) {
        this.maHtr = maHtr;
    }
}
