package com.lecongtien.cinema.payload.response;

public class DataListSystem {
    private int maHeThongRap;
    private String tenHeThongRap;
    private String logo;
    private Object listCumrap;

    public int getMaHeThongRap() {
        return maHeThongRap;
    }

    public void setMaHeThongRap(int maHeThongRap) {
        this.maHeThongRap = maHeThongRap;
    }

    public String getTenHeThongRap() {
        return tenHeThongRap;
    }

    public void setTenHeThongRap(String tenHeThongRap) {
        this.tenHeThongRap = tenHeThongRap;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Object getListCumrap() {
        return listCumrap;
    }

    public void setListCumrap(Object listCumrap) {
        this.listCumrap = listCumrap;
    }
}
