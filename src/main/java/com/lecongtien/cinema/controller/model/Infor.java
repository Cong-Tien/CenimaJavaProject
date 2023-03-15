package com.lecongtien.cinema.controller.model;

import com.lecongtien.cinema.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Infor{
    private String email;
    private List<TicketEntity> list;
    private String maLC;
}
