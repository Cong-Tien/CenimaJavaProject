package com.lecongtien.cinema.entity;

import javax.persistence.*;

@Entity(name = "phim")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_phim")
    private String tenPhim;
    @Column(name = "trailer")
    private String trailer;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "thoi_luong")
    private int thoiLuong;
    @Column(name = "danh_gia")
    private int danhGia;
    @Column(name = "ngay_khoi_chieu")
    private String ngayKhoiChieu;
    @Column(name = "ngay_ket_thuc")
    private String ngayKetThuc;
    @Column(name = "san_xuat")
    private String sanXuat;
    @Column(name = "dao_dien")
    private String daoDien;
    @Column(name = "nam_sx")
    private int namSX;
    @Column(name = "ap_phich")
    private String poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public String getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(String ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getSanXuat() {
        return sanXuat;
    }

    public void setSanXuat(String sanXuat) {
        this.sanXuat = sanXuat;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
