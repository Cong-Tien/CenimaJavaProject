package com.lecongtien.cinema.model;

import lombok.Data;

import java.util.Date;

@Data
public class CinemaImportModel {
    private String nameCinema;
    private String logoCinema;
    private String infor;
    private String mapLink;
    private Date ngayMoCua;
    private Date gioMoCua;
    private String heThongRap;

}
