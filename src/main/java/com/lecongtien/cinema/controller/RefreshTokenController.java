package com.lecongtien.cinema.controller;

import com.google.gson.Gson;
import com.lecongtien.cinema.entity.UserEntity;
import com.lecongtien.cinema.jwt.JwtTokenHelper;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.payload.response.DataTokenResponse;
import com.lecongtien.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    private long expiredDate = 8 * 60 * 60 * 1000;
    private long refreshExpiredDate = 80 * 60 * 60 * 1000;
    @Autowired
    UserRepository userRepository;
    private Gson gson = new Gson();
    @PostMapping("")
    public ResponseEntity<?> index(@RequestParam("accessToken") String token){
        DataResponse dataResponse= new DataResponse();
        if(jwtTokenHelper.validateToken(token)){
            //token hợp lệ
            String json = jwtTokenHelper.decodeToken(token);
            Map<String,Object> map = gson.fromJson(json,Map.class);
            System.out.println("kiem tra: " + json + " - "  + map.get("type").toString());
            if(StringUtils.hasText(map.get("type").toString())
                    && map.get("type").toString().equals("refresh")){
//                String username = jwtTokenHelper.decodeToken(token);
//                System.out.println("kiem tra: " + username);
                String tokenAuthen = jwtTokenHelper
                        .generateToken(map.get("username").toString(),"authen",expiredDate);
                String refreshToken = jwtTokenHelper
                        .generateToken(map.get("username").toString(),"refresh",refreshExpiredDate);
                String decodeToken = jwtTokenHelper.decodeToken(token);

//                DataTokenResponse dataTokenResponse = new DataTokenResponse();
//                dataTokenResponse.setToken(token);
//                dataTokenResponse.setRefreshToken(refreshToken);


                dataResponse.setStatus(HttpStatus.OK.value());
                dataResponse.setDesc("Xin chao: " + decodeToken);
                List<UserEntity> list = userRepository.findByEmail(map.get("username").toString());
                if(list.size()>0){
                    UserEntity user = list.get(0);
                    user.setAccessToken(tokenAuthen);
                    user.setRefreshToken(refreshToken);
                    userRepository.save(user);
                    dataResponse.setData(list.get(0));
                }
                else{
                    dataResponse.setData(null);
                }
                dataResponse.setSuccess(true);
            }
            else {
                dataResponse.setStatus(HttpStatus.OK.value());
                dataResponse.setSuccess(true);
                dataResponse.setDesc("That bai");
                dataResponse.setData("");
            }

        }
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
