package com.lecongtien.cinema.controller;

import com.google.gson.Gson;
import com.lecongtien.cinema.entity.MovieEntity;
import com.lecongtien.cinema.entity.ShowTimeEntity;
import com.lecongtien.cinema.entity.TicketEntity;
import com.lecongtien.cinema.entity.UserEntity;
import com.lecongtien.cinema.jwt.JwtTokenHelper;
import com.lecongtien.cinema.payload.request.SignInRequest;
import com.lecongtien.cinema.payload.response.DataResponse;
import com.lecongtien.cinema.payload.response.DataTokenResponse;
import com.lecongtien.cinema.payload.response.HistoryTicketRespone;
import com.lecongtien.cinema.repository.*;
import com.lecongtien.cinema.service.LoginService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowtimeRepository showtimeRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieRepositoty movieRepositoty;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    RoomRepository roomRepository;
    private Gson gson = new Gson();
    @Autowired
    AuthenticationManager authenticationManager;
    private long expiredDate = 8 * 60 * 60 * 1000;
    private long refreshExpiredDate = 80 * 60 * 60 * 1000;
    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request){
        //boolean isSuccess = loginService.checkLogin(request.getUsername(),request.getPassword());
        UsernamePasswordAuthenticationToken authenRequest
                = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        Authentication auth = authenticationManager.authenticate(authenRequest);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        String token = jwtTokenHelper
                .generateToken(request.getUsername(),"authen",expiredDate);
        String refreshToken = jwtTokenHelper
                .generateToken(request.getUsername(),"refresh",refreshExpiredDate);
        String decodeToken = jwtTokenHelper.decodeToken(token);

//        DataTokenResponse dataTokenResponse = new DataTokenResponse();
//        dataTokenResponse.setToken(token);
//        dataTokenResponse.setRefreshToken(refreshToken);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

//        String dateInString = "12/25/2013 10:11:59";
//        DateTime dateTime = DateTime.parse(dateInString, formatter);


        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xin chao: " + decodeToken);
        List<UserEntity> list = userRepository.findByEmail(request.getUsername());
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
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/signup")
    public ResponseEntity<?> signin(@Valid @RequestBody  UserEntity request){

        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setSdt(request.getSdt());
        user.setNgaySinh("01/01/2001");
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        UsernamePasswordAuthenticationToken authenRequest
                = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        Authentication auth = authenticationManager.authenticate(authenRequest);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        String token = jwtTokenHelper
                .generateToken(request.getEmail(),"authen",expiredDate);
        String refreshToken = jwtTokenHelper
                .generateToken(request.getEmail(),"refresh",refreshExpiredDate);
        String decodeToken = jwtTokenHelper.decodeToken(token);

//        DataTokenResponse dataTokenResponse = new DataTokenResponse();
//        dataTokenResponse.setToken(token);
//        dataTokenResponse.setRefreshToken(refreshToken);

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xin chao: " + decodeToken);
        List<UserEntity> list = userRepository.findByEmail(request.getEmail());
        if(list.size()>0){
            UserEntity user1 = list.get(0);
            user1.setAccessToken(token);
            user1.setRefreshToken(refreshToken);
            userRepository.save(user);
            dataResponse.setData(list.get(0));
        }
        else{
            dataResponse.setData(null);
        }
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/facebook")
    public ResponseEntity<?> signinFacebook(@RequestParam("email") String request){
        if(userRepository.findByEmail(request).size()>0){
            UsernamePasswordAuthenticationToken authenRequest
                    = new UsernamePasswordAuthenticationToken(request,userRepository.findByEmail(request).get(0).getPassword());
            Authentication auth = authenticationManager.authenticate(authenRequest);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

        }
        else {
            UserEntity user = new UserEntity();
//            user.setEmail();
//            user.setName();
//            user.setActive();
//            user.setRefreshToken();
//            user.setAccessToken();
//            user.setSdt();
//            user.setNgaySinh();
            passwordEncoder.encode("12345678");
            userRepository.save(user);
            UsernamePasswordAuthenticationToken authenRequest
                    = new UsernamePasswordAuthenticationToken(request,userRepository.findByEmail(request).get(0).getPassword());
            Authentication auth = authenticationManager.authenticate(authenRequest);

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

        }


        String token = jwtTokenHelper
                .generateToken(request,"authen",expiredDate);
        String refreshToken = jwtTokenHelper
                .generateToken(request,"refresh",refreshExpiredDate);
        String decodeToken = jwtTokenHelper.decodeToken(token);

        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Xin chao: " + decodeToken);
        List<UserEntity> list = userRepository.findByEmail(request);
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
    }
    @PostMapping("/abc")
    public ResponseEntity<?> signinFacebook(){

        System.out.println("Da di qua day");
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
    @GetMapping("")
    public ResponseEntity<?> historyUser(@RequestParam("accessToken") String token){
        String json = jwtTokenHelper.decodeToken(token);
        Map<String,Object> map = gson.fromJson(json,Map.class);
        List<UserEntity> user= userRepository.findByEmail(map.get("username").toString());
        List<TicketEntity> tickets = ticketRepository.findByIdUser(user.size()>0 ? user.get(0).getId():-1);
        List<HistoryTicketRespone> historyTicketRespones = new ArrayList<>();
        for (TicketEntity ticket:tickets) {
            HistoryTicketRespone historyTicket = new HistoryTicketRespone();
            historyTicket.setTicket(ticket);
            ShowTimeEntity showtime = showtimeRepository.findByIdShowtime(ticket.getIdShowtime());
            historyTicket.setThoiGian(showtime.getShowtime());
            MovieEntity movie = movieRepositoty.findById(showtime.getMaPhim());
            historyTicket.setTenPhim(movie.getTenPhim());
            historyTicket.setHinhAnh(movie.getPoster());
            historyTicket.setPhongChieu(roomRepository.findByIdRoom(showtime.getIdRoom()).getNameRoom());
            historyTicket.setDiaChi(cinemaRepository.findByIdCinema(showtime.getIdCinema()).getInfor());
            historyTicket.setLogoCinema(cinemaRepository.findByIdCinema(showtime.getIdCinema()).getLogoCinema());
            historyTicketRespones.add(historyTicket);
        }
        DataResponse dataResponse= new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Thanh cong");
        dataResponse.setSuccess(true);
        dataResponse.setData(historyTicketRespones);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
