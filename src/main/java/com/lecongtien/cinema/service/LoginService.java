package com.lecongtien.cinema.service;

import com.lecongtien.cinema.entity.UserEntity;

import java.util.List;

public interface LoginService {
    boolean checkLogin(String email,String password);
    UserEntity checkLogin(String email);
    List<UserEntity> getUser();
}
