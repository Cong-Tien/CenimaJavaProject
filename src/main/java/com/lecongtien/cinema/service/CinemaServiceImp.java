package com.lecongtien.cinema.service;

import com.lecongtien.cinema.entity.CinemaEntity;
import com.lecongtien.cinema.entity.SystemEntity;
import com.lecongtien.cinema.model.*;
import com.lecongtien.cinema.repository.CinemaRepository;
import com.lecongtien.cinema.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImp implements CinemaService{
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SystemRepository systemRepository;
    @Override
    public ImportResponse importCinemaExcel(ExcelResponseModel listOfCinema) {
        ImportResponse importResponse = new ImportResponse();
        int totalSuccess = 0;
        HashMap<Integer, Object> dataList = listOfCinema.getData();
        List<ExcelErrorDetail> errorList = listOfCinema.getErrorDetails();
        List<ErrorModel> errorModelList = ErrorModel.importErrorDetails(errorList);
        List<SystemEntity> systemEntityList = systemRepository.findAll();
        List<String> systemListName = systemEntityList.stream().map(SystemEntity::getTenHtr).collect(Collectors.toList());
        List<Integer> systemListId = systemEntityList.stream().map(SystemEntity::getMaHtr).collect(Collectors.toList());
        for (Map.Entry<Integer,Object> entry: dataList.entrySet()){
            CinemaImportModel cinemaImportModel = (CinemaImportModel) entry.getValue();
            CinemaEntity cinema = cinemaRepository.findByNameCinema(cinemaImportModel.getNameCinema());

            if (ObjectUtils.isEmpty(cinema) && systemListName.contains(cinemaImportModel.getHeThongRap())){
                CinemaEntity cinemaInsert = new CinemaEntity();
                cinemaInsert.setLogoCinema(cinemaImportModel.getLogoCinema());
                cinemaInsert.setNameCinema(cinemaImportModel.getNameCinema());
                cinemaInsert.setInfor(cinemaImportModel.getInfor());
                cinemaInsert.setMapLink(cinemaImportModel.getMapLink());
                cinemaInsert.setGioMoCua(cinemaImportModel.getGioMoCua());
                cinemaInsert.setNgayMoCua(cinemaImportModel.getNgayMoCua());
                cinemaInsert.setMaHtr(systemRepository.findByTenHtr(cinemaImportModel.getHeThongRap()).getMaHtr());

                cinemaRepository.save(cinemaInsert);
                totalSuccess++;
            }
            else {
                errorModelList.add(new ErrorModel(entry.getKey(), ErrorLineModel.importCinemaErrorDetail(cinema,systemListName, cinemaImportModel.getHeThongRap())));
            }
        }

        ErrorModel.sortModelByline(errorModelList);
        importResponse.setTotalRows(listOfCinema.getTotalRows());
        importResponse.setErrorRows(errorModelList.size());
        importResponse.setSuccessRows(totalSuccess);
        importResponse.setErrorList(errorModelList);
        return importResponse;
    }
}
