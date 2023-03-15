package com.lecongtien.cinema.entity;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "loai_ve")
    private int loaiVe;
    @Column(name = "ngay_mua_ve")
    private Date ngayMuaVe;
    @Column(name = "ghe")
    private String ghe;
    @Column(name = "loai_ghe")
    private String loaiGhe;
    @Column(name = "trang_thai")
    private int trangThai;
    @Column(name = "da_dat")
    private boolean daDat;
    @Column(name = "gia_ve")
    private int giaVe;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_showtime")
    private int idShowtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(int loaiVe) {
        this.loaiVe = loaiVe;
    }

    public Date getNgayMuaVe() {
        return ngayMuaVe;
    }

    public void setNgayMuaVe(Date ngayMuaVe) {
        this.ngayMuaVe = ngayMuaVe;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isDaDat() {
        return daDat;
    }

    public void setDaDat(boolean daDat) {
        this.daDat = daDat;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(int idShowtime) {
        this.idShowtime = idShowtime;
    }
}
