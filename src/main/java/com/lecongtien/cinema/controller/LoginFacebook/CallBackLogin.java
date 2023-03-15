package com.lecongtien.cinema.controller.LoginFacebook;

import com.google.gson.Gson;
import com.lecongtien.cinema.entity.UserEntity;
import com.lecongtien.cinema.jwt.JwtTokenHelper;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.repository.UserRepository;
import com.lecongtien.cinema.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facebook")
public class CallBackLogin {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    private Gson gson = new Gson();
    @Autowired
    LoginService loginService;
    private long expiredDate = 8 * 60 * 60 * 1000;
    private long refreshExpiredDate = 80 * 60 * 60 * 1000;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @GetMapping("")
    public ResponseEntity<?> getCode(@RequestParam("access") String access){
        FBGraph fbGraph = new FBGraph(access);
        String graph = fbGraph.getFBGraph();
        Map<String, Object> profile = fbGraph.getGraphData(graph);

        System.out.println(profile.get("email"));
        if(userRepository.findByEmail((String) profile.get("email")).size()>0){
            UsernamePasswordAuthenticationToken authenRequest
                    = new UsernamePasswordAuthenticationToken(profile.get("email"),userRepository.findByEmail((String) profile.get("email")).get(0).getPassword());
            Authentication auth = authenticationManager.authenticate(authenRequest);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

        }
        else {
            UserEntity user = new UserEntity();
            user.setEmail((String) profile.get("email"));
            user.setName((String) profile.get("name"));
//            user.setRefreshToken();
//            user.setAccessToken();
//            user.setSdt();
            user.setNgaySinh((String) profile.get("birthday"));
            user.setPassword(passwordEncoder.encode("12345678"));
            userRepository.save(user);
            UsernamePasswordAuthenticationToken authenRequest
                    = new UsernamePasswordAuthenticationToken((String) profile.get("email"),userRepository.findByEmail((String) profile.get("email")).get(0).getPassword());
            Authentication auth = authenticationManager.authenticate(authenRequest);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

        }


        String token = jwtTokenHelper
                .generateToken((String) profile.get("email"),"authen",expiredDate);
        String refreshToken = jwtTokenHelper
                .generateToken((String) profile.get("email"),"refresh",refreshExpiredDate);
        String decodeToken = jwtTokenHelper.decodeToken(token);

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xin chao: " + decodeToken);
        List<UserEntity> list = userRepository.findByEmail((String) profile.get("email"));
        if(list.size()>0){
            UserEntity user = list.get(0);
            user.setAccessToken(token);
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
            dataResponse.setData(list.get(0));
        }
        else{
            dataResponse.setData(null);
        }
        dataResponse.setSuccess(true);
        System.out.println("Da di qua day");

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        //return profile;
    }


}
