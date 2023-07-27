package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.model.CinemaImportModel;
import com.lecongtien.cinema.model.ErrorModel;
import com.lecongtien.cinema.model.ImportResponse;
import com.lecongtien.cinema.model.common.BaseResponse;
import com.lecongtien.cinema.service.CinemaService;
import com.lecongtien.cinema.util.CsvUtils;
import com.lecongtien.cinema.util.ExcelUtils;
import com.lecongtien.cinema.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/import")
public class ImportFileController {
    @Autowired
    private CinemaService cinemaService;

    @PostMapping("cinema")
    public BaseResponse<ImportResponse> importCinema(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType) throws IOException {
        String excelType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        String csvType = "text/csv";
        if (excelType.equals(file.getContentType())){
            switch (fileType) {
                case "cinema":
                    return returnBaseResponse(cinemaService.importCinemaExcel(
                            ExcelUtils.readFromExcel(
                                    file.getInputStream(),
                                    JsonUtils.convertJsonToPojo(JsonUtils.readFileAsString("/jsonconfig/CinemaReadConfig.json")),
                                    CinemaImportModel.class
                            )
                    ));
                default:
                    return BaseResponse.badRequest("Not valid excel file.");
            }
        } else if (csvType.equals(file.getContentType())) {
            switch (fileType) {
                case "cinema":
                    return returnBaseResponse(cinemaService.importCinemaExcel(
                            CsvUtils.readCsv(
                                    file.getInputStream(),
                                    JsonUtils.convertJsonToPojo(JsonUtils.readFileAsString("/jsonconfig/CinemaReadConfig.json")),
                                    CinemaImportModel.class
                            )
                    ));
                default:
                    return BaseResponse.badRequest("Not valid csv file.");
            }
        } else {
            return BaseResponse.badRequest("Please select excel or csv file.");
        }

    }

    public BaseResponse<ImportResponse> returnBaseResponse(ImportResponse response){
        if (response.getSuccessRows() == response.getTotalRows() && response.getErrorRows() == 0){
           return BaseResponse.success(response);
        }
        return BaseResponse.badRequest(response);
    }
}
