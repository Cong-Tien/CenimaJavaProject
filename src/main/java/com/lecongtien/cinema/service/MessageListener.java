package com.lecongtien.cinema.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
//    @RabbitListener(queues = "convertpdf")
//    public void receivedMessage(String message){
//        System.out.println("Ktra message: " + message);
//    }
}
