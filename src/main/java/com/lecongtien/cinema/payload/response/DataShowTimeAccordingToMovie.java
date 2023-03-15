package com.lecongtien.cinema.payload.response;

public class DataShowTimeAccordingToMovie {
    private int maPhim;
    private String tenPhim;
    private String hinhAnh;
    private boolean hot ;
    private Object listShowTime;

    public int getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public Object getListShowTime() {
        return listShowTime;
    }

    public void setListShowTime(Object listShowTime) {
        this.listShowTime = listShowTime;
    }
}
