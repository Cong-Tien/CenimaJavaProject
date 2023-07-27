package com.lecongtien.cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportResponse {
    private int totalRows;
    private int successRows;
    private int errorRows;
    private List<ErrorModel> errorList;
}
