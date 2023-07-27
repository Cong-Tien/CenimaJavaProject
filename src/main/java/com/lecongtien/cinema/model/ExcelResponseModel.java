package com.lecongtien.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcelResponseModel {
    HashMap<Integer, Object> data;
    Integer totalRows;
    List<ExcelErrorDetail> errorDetails;
    Status status;
}
