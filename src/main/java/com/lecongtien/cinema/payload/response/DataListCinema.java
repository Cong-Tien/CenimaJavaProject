package com.lecongtien.cinema.payload.response;

public class DataListCinema {
    private int maCumRap;
    private String tenCumRap;
    private String hinhAnh;
    private String diaChi;
    private Object danhSachPhim;

    public int getMaCumRap() {
        return maCumRap;
    }

    public void setMaCumRap(int maCumRap) {
        this.maCumRap = maCumRap;
    }

    public String getTenCumRap() {
        return tenCumRap;
    }

    public void setTenCumRap(String tenCumRap) {
        this.tenCumRap = tenCumRap;
    }

    public String getHinhAnh() {
        return hinhAnh;
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

    public Object getDanhSachPhim() {
        return danhSachPhim;
    }

    public void setDanhSachPhim(Object danhSachPhim) {
        this.danhSachPhim = danhSachPhim;
    }
}
