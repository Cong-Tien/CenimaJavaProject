package com.lecongtien.cinema.controller.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueue {
    final String QUEUE_NAME = "convertpdf";
    final String EXCHANGE_NAME = "customexchange";
    final String ROUTING_KEY ="";

    // Tạo ra 1 queue mới hoặc sd queue đã tồn tại
//    @Bean
//    Queue queue(){
//        return new Queue(QUEUE_NAME,true);
//    }
//    @Bean
//    DirectExchange exchange(){
//        return new DirectExchange(EXCHANGE_NAME,true,false);
//    }
//    @Bean
//    Binding binding(Queue queue,DirectExchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }

}
