package com.lecongtien.cinema.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private Gson gson = new Gson();
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //cut header và lấy token
        String token = getTokenFromHeader(request);
        System.out.println("Token: " + token);
        if(token != null){
            // kiểm tra token có phải do hệ thống sinh ra hay không
            if(jwtTokenHelper.validateToken(token)){
                //token hợp lệ
                String json = jwtTokenHelper.decodeToken(token);
                Map<String,Object> map = gson.fromJson(json,Map.class);
                System.out.println("kiem tra: " + json + " - "  + map.get("type").toString());
                if(StringUtils.hasText(map.get("type").toString())
                        && !map.get("type").toString().equals("refresh")){
//                String username = jwtTokenHelper.decodeToken(token);
//                System.out.println("kiem tra: " + username);
                    UsernamePasswordAuthenticationToken authRequest
                            =new UsernamePasswordAuthenticationToken("","", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authRequest);
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        // Lấy giá trị token của header có key là authorization
        String strToken = request.getHeader("Authorization");
        System.out.println("header "+ strToken);

        if(StringUtils.hasText(strToken) && strToken.startsWith("Bearer ")){
            //Xử lý khi token hợp lệ
            //subString dùng để cut chuỗi
            String finalToken = strToken.substring(7);
            return finalToken;
        }
        else{
            return null;
        }
    }
}
