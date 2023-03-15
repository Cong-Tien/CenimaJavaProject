package com.lecongtien.cinema.controller.RabbitMQ;

import com.lecongtien.cinema.service.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerRabbitMQ {
    final String QUEUE_NAME = "convertpdf";
    final String EXCHANGE_NAME = "customexchange";
    final String ROUTING_KEY ="";
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
//            , MessageListenerAdapter messageListenerAdapter){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME);
//        //container.setMessageListener(messageListenerAdapter);
//
//        return  container;
//    }
//    @Bean
//    MessageListenerAdapter listenerAdapter(MessageListener messageListener){
//        return new MessageListenerAdapter(messageListener,"receivedMessage");
//    }
//    @Bean
//    ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("localhost");
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        return connectionFactory;
//    }
}
