package com.lecongtien.cinema.service;

import com.lecongtien.cinema.entity.ShowTimeEntity;
import com.lecongtien.cinema.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowtimeServiceImp implements ShowtimeService{
    @Autowired
    ShowtimeRepository showtimeRepository;

    @Override
    public List<ShowTimeEntity> getAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public boolean deleteShowtimeById(int id) {
        try{
            showtimeRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean updateShowtimeById(ShowTimeEntity showTimeEntity) {
       try{
           showtimeRepository.save(showTimeEntity);
           return true;
       }
       catch (Exception ex){
           return false;
       }
    }

    @Override
    public boolean insertShowtime(ShowTimeEntity showTimeEntity) {
        try{
            showtimeRepository.save(showTimeEntity);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
}
