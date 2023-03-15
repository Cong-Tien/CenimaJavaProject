package com.lecongtien.cinema.payload.response;

import com.lecongtien.cinema.entity.MovieEntity;

public class MovieDetailShowtime extends MovieEntity {
    private Object heThongRapChieu;

    public Object getHeThongRapChieu() {
        return heThongRapChieu;
    }

    public void setHeThongRapChieu(Object heThongRapChieu) {
        this.heThongRapChieu = heThongRapChieu;
    }
}
