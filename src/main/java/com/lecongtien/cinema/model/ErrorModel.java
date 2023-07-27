package com.lecongtien.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class ErrorModel {
    private int line;
    private List<ErrorLineModel> errorLineList;

    public static List<ErrorModel> importErrorDetails(List<ExcelErrorDetail> errorList){
        List<ErrorModel> errorModelList = new ArrayList<>();
        for (ExcelErrorDetail excelErrorDetail: errorList) {
            List<ErrorLineModel> errorLineModels = new ArrayList<>();

            HashMap<String, String> errorMap = excelErrorDetail.getDetails();

            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                errorLineModels.add(new ErrorLineModel(entry.getKey(), entry.getValue()));
            }
            errorModelList.add(new ErrorModel(excelErrorDetail.getRowIndex(),errorLineModels));
        }
        return errorModelList;
    }

    public static void sortModelByline(List<ErrorModel> models){
        models.sort(Comparator.comparingInt(ErrorModel::getLine));
    }
}
