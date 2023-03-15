package com.lecongtien.cinema.payload.response;

public class HistoryTicketRespone {
    private String hinhAnh;
    private String diaChi;
    private String thoiGian;
    private String tenPhim;
    private String logoCinema;
    private String phongChieu;
    private Object ticket;

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getLogoCinema() {
        return logoCinema;
    }

    public void setLogoCinema(String logoCinema) {
        this.logoCinema = logoCinema;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(String phongChieu) {
        this.phongChieu = phongChieu;
    }

    public Object getTicket() {
        return ticket;
    }

    public void setTicket(Object ticket) {
        this.ticket = ticket;
    }
}
