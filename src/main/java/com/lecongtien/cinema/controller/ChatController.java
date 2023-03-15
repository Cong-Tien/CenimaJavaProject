package com.lecongtien.cinema.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lecongtien.cinema.controller.model.Infor;
import com.lecongtien.cinema.controller.model.Message;
import com.lecongtien.cinema.entity.TicketEntity;
import com.lecongtien.cinema.repository.TicketRepository;
import com.lecongtien.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    UserRepository userRepository;

    @MessageMapping("/datGhe")
    @SendTo("/booking/danhSachGheDat")
    private List<TicketEntity> recivePublicMess(@Payload JsonNode infor){
        ObjectMapper mapper = new ObjectMapper();
        List<TicketEntity> list = new ArrayList<>();
        try {
            Infor infor1 = mapper.readValue(mapper.writeValueAsString(infor) ,Infor.class);
            //Optional<TicketEntity> tickets = ticketRepository.findById(infor1.getList().get(0).getId());
            List<TicketEntity> arrSeat = infor1.getList();
            List<TicketEntity> tickets = ticketRepository.findByTrangThaiAndIdShowtimeAndIdUser(1,Integer.parseInt(infor1.getMaLC()),userRepository.findByEmail(infor1.getEmail()).get(0).getId());

            for (TicketEntity ticket: tickets) {
                if(arrSeat.size()>0){
                    for (TicketEntity seat: arrSeat) {
                        if(ticket.getId() != seat.getId()){
                            ticket.setTrangThai(0);
                            ticketRepository.save(ticket);
                        }
                    }
                }else {
                    ticket.setTrangThai(0);
                    ticketRepository.save(ticket);
                }
            }

            for (TicketEntity ticket : arrSeat) {
                ticket.setTrangThai(1);
                ticket.setIdUser(userRepository.findByEmail(infor1.getEmail()).get(0).getId());
                ticketRepository.save(ticket);
            }
            list = ticketRepository.findByTrangThaiAndIdShowtime(1, Integer.parseInt(infor1.getMaLC()));
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @MessageMapping("/muaVe")
    @SendTo({"/booking/danhSachGheDat","/booking/datVeThanhCong"})
    private List<TicketEntity> muaVe(@Payload JsonNode infor){
        ObjectMapper mapper = new ObjectMapper();
        List<TicketEntity> list = new ArrayList<>();
        try {
            Infor infor1 = mapper.readValue(mapper.writeValueAsString(infor) ,Infor.class);
            //Optional<TicketEntity> tickets = ticketRepository.findById(infor1.getList().get(0).getId());
            List<TicketEntity> tickets = ticketRepository.findByTrangThaiAndIdShowtimeAndIdUser(1,Integer.parseInt(infor1.getMaLC()),Integer.parseInt(infor1.getEmail()));

            for (TicketEntity ticket: tickets) {
               ticket.setTrangThai(0);
               ticket.setDaDat(true);
               ticket.setIdUser(Integer.parseInt(infor1.getEmail()));
               ticketRepository.save(ticket);
            }
            list = ticketRepository.findByTrangThaiAndIdShowtime(1, Integer.parseInt(infor1.getMaLC()));
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//    @MessageMapping("/muaVe")
//    @SendTo("/booking/datVeThanhCong")
//    private List<TicketEntity> muaVeThanhCong(@Payload JsonNode infor){
//        ObjectMapper mapper = new ObjectMapper();
//        List<TicketEntity> list = new ArrayList<>();
//        try {
//            Infor infor1 = mapper.readValue(mapper.writeValueAsString(infor) ,Infor.class);
//
//            list = ticketRepository.findByIdShowtime(Integer.parseInt(infor1.getMaLC()));
//            return list;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @MessageMapping("/huyDat")
    @SendTo("/booking/danhSachGheDat")
    private List<TicketEntity> huyDatGhe(@Payload JsonNode infor){
        ObjectMapper mapper = new ObjectMapper();
        List<TicketEntity> list = new ArrayList<>();
        try {
            Infor infor1 = mapper.readValue(mapper.writeValueAsString(infor) ,Infor.class);
            List<TicketEntity> arrSeat = ticketRepository.findByTrangThaiAndIdShowtimeAndIdUser(1,Integer.parseInt(infor1.getMaLC()),Integer.parseInt(infor1.getEmail()));
            for (TicketEntity ticket : arrSeat) {
                ticket.setTrangThai(0);
                ticket.setIdUser(0);
                ticketRepository.save(ticket);
            }
            list = ticketRepository.findByTrangThaiAndIdShowtime(1, Integer.parseInt(infor1.getMaLC()));
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @MessageMapping("/loadDanhSachGhe")
    @SendTo("/booking/danhSachGheDat")
    private List<TicketEntity> loadDanhSachGhe(@Payload JsonNode infor){
        ObjectMapper mapper = new ObjectMapper();
        List<TicketEntity> list = new ArrayList<>();
        try {
            Infor infor1 = mapper.readValue(mapper.writeValueAsString(infor) ,Infor.class);
            List<TicketEntity> arrSeat = ticketRepository.findByTrangThaiAndIdShowtimeAndIdUserIsNot(1,Integer.parseInt(infor1.getMaLC()),0);
            return arrSeat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @MessageMapping("/private-message")
    private Message recivePrivateMess(@Payload Message message){
        simpMessagingTemplate.
                convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }
}
