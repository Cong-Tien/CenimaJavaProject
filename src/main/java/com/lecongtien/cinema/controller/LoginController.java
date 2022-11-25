package com.lecongtien.cinema.controller;

import com.lecongtien.cinema.payload.request.SignInRequest;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.UserRepository;
import com.lecongtien.cinema.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request){
        boolean isSuccess = loginService.checkLogin(request.getUsername(),request.getPassword());

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(isSuccess);
        dataResponse.setData(userRepository.findByEmailAndPassword(request.getUsername(),request.getPassword()).get(0));
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> signin(){

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(loginService.getUser());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
