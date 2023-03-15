package com.lecongtien.cinema.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class MovieRequest {
    private int maPhim;
    private String tenPhim;
    private String trailer;
    private String moTa;
    private String ngayKhoiChieu;
    private boolean sapChieu;
    private boolean dangChieu;
    private boolean hot;
    private int danhGia;
    private MultipartFile hinhAnh;

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

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(String ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public boolean isSapChieu() {
        return sapChieu;
    }

    public void setSapChieu(boolean sapChieu) {
        this.sapChieu = sapChieu;
    }

    public boolean isDangChieu() {
        return dangChieu;
    }

    public void setDangChieu(boolean dangChieu) {
        this.dangChieu = dangChieu;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public MultipartFile getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(MultipartFile hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
