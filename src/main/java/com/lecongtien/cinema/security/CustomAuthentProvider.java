package com.lecongtien.cinema.security;

import com.lecongtien.cinema.entity.UserEntity;
import com.lecongtien.cinema.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CustomAuthentProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Xử lý đăng nhập thành công hay thất bại
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("Username: " + userName + " Password: " + password);
        //boolean isSuccess = loginService.checkLogin(userName,password);
        UserEntity userEntity = loginService.checkLogin(userName);
        //System.out.println(userEntity.getEmail()+"++++++==");
        //System.out.println("Kiem tra: " + isSuccess);
        if(userEntity!=null){
            boolean isMatchingPassword = passwordEncoder.matches(password,userEntity.getPassword());
            if(isMatchingPassword || password==userEntity.getPassword()){
                //return new UsernamePasswordAuthenticationToken(userEntity.getEmail(),userEntity.getPassword(),new ArrayList<>());
//                List<SimpleGrantedAuthority> list = new ArrayList<>();
//                list.add()
                return new UsernamePasswordAuthenticationToken(userEntity.getEmail()
                        ,userEntity.getPassword()

                        , Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")));
            }


            else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
