package com.lecongtien.cinema.model;

import com.lecongtien.cinema.constant.ErrorConstant;
import com.lecongtien.cinema.entity.CinemaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorLineModel {
    private String field;
    private String message;

    public static List<ErrorLineModel> importCinemaErrorDetail(CinemaEntity cinema, List<String> systemCinemas, String systemCinema){
        List<ErrorLineModel> errorLineModelList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(cinema)){
            errorLineModelList.add(new ErrorLineModel("Cinema", cinema.getNameCinema() + ErrorConstant.MESSAGE_CINEMA_NAME_ALREADY_EXIST));
        }
        if (!systemCinemas.contains(systemCinema)){
            errorLineModelList.add(new ErrorLineModel("System cinema", systemCinema + ErrorConstant.MESSAGE_SYSTEM_CINEMA_NOT_EXIST));
        }
        return errorLineModelList;
    }
}
