package com.lecongtien.cinema.payload.response;

public class TicketResponse {
    private Object thongTinPhim;
    private Object danhSachGhe;

    public Object getThongTinPhim() {
        return thongTinPhim;
    }

    public void setThongTinPhim(Object thongTinPhim) {
        this.thongTinPhim = thongTinPhim;
    }

    public Object getDanhSachGhe() {
        return danhSachGhe;
    }

    public void setDanhSachGhe(Object danhSachGhe) {
        this.danhSachGhe = danhSachGhe;
    }
}
