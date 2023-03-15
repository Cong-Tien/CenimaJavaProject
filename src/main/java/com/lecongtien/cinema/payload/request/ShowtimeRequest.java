package com.lecongtien.cinema.payload.request;

public class ShowtimeRequest {
    private int maPhim;
    private String ngayChieuGioChieu;
    private int maRap;
    private int maPhong;
    private int giaVe;

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getNgayChieuGioChieu() {
        return ngayChieuGioChieu;
    }

    public void setNgayChieuGioChieu(String ngayChieuGioChieu) {
        this.ngayChieuGioChieu = ngayChieuGioChieu;
    }

    public int getMaRap() {
        return maRap;
    }

    public void setMaRap(int maRap) {
        this.maRap = maRap;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
}
