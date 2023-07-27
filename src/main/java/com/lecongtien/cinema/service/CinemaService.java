package com.lecongtien.cinema.service;

import com.lecongtien.cinema.model.ExcelResponseModel;
import com.lecongtien.cinema.model.ImportResponse;
import org.springframework.stereotype.Service;

public interface CinemaService {
    ImportResponse importCinemaExcel(ExcelResponseModel listOfCinema);
}
