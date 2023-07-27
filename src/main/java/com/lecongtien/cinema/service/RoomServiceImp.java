package com.lecongtien.cinema.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lecongtien.cinema.model.DTO.RoomDTO;
import com.lecongtien.cinema.entity.RoomEntity;
import com.lecongtien.cinema.entity.ShowTimeEntity;
import com.lecongtien.cinema.repository.RoomRepository;
import com.lecongtien.cinema.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomServiceImp implements RoomService{
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    //@Cacheable("detail_room")
    public List<RoomDTO> getTurnover() {
        String key = "cache";
        Gson gson = new Gson();
        List<RoomDTO> dtos = new ArrayList<>();
        if(redisTemplate.hasKey(key)){
            // key có  tồn tại
            String data = (String) redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            List<RoomDTO> infor1 = null;
            try {
                infor1 = mapper.readValue(data , List.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            dtos = infor1;
        }
        else{
            //key ko tồn tại

            List<RoomEntity> rooms = roomRepository.findAll();
            for (RoomEntity room:rooms) {
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setRoomName(room.getNameRoom());
                roomDTO.setTotalSeat(room.getTotalSeat());
                float turnover = 0;
                float sum=0;
                for (ShowTimeEntity showtime: showtimeRepository.findByIdRoom(room.getIdRoom())) {
                    sum+=showtime.getPrice();
                }
                if(showtimeRepository.findByIdRoom(room.getIdRoom()).size()>0){
                    turnover=sum/showtimeRepository.findByIdRoom(room.getIdRoom()).size();
                }
                roomDTO.setAvgTurnover(turnover);
                dtos.add(roomDTO);
            }
            System.out.println("Hello");
            String json = gson.toJson(dtos);
            redisTemplate.opsForValue().set(key,json);
        }

        return dtos;
    }
    @Override
    @CacheEvict(value = "dataSystem",allEntries = true)
    public void clearAllCache(){

    }
}
