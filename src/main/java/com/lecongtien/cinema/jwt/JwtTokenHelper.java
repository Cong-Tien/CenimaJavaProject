package com.lecongtien.cinema.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {
    private final String strKey  = "xJHDonkgbMOgIGNodeG7l2kgbcOjIGjDs2EgMjU2IGJpdCBj4bunYSBsw6ogY8O0bmcgdGnhur9uIMSReg=="; //Chuối base 64
    private Gson gson = new Gson();
    public String generateToken(String data, String type,long expiredDate){
        Date now =new Date();
        Date dateExpired = new Date(now.getTime()+expiredDate);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        Map<String,Object> subJectData = new HashMap<>();
        subJectData.put("username",data);
        subJectData.put("type",type);

        String json = gson.toJson(subJectData);

        String token = Jwts.builder()
                .setSubject(json)//lưu trữ dữ liệu vào token kiểu String
                .setIssuedAt(now)
                .setExpiration(dateExpired) // Thời gian hết hạn của token
                .signWith(secretKey,SignatureAlgorithm.HS256)// Thuậ toán mã hóa và secrect key
                .compact(); // Trả ra token đã được mã hóa
        return token;
    }
//public String generateToken(String data,long expiredDate){
//    Date now =new Date();
//    Date dateExpired = new Date(now.getTime()+expiredDate);
//    SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
//
//    String token = Jwts.builder()
//            .setSubject(data)//lưu trữ dữ liệu vào token kiểu String
//            .setIssuedAt(now)
//            .setExpiration(dateExpired) // Thời gian hết hạn của token
//            .signWith(secretKey,SignatureAlgorithm.HS256)// Thuậ toán mã hóa và secrect key
//            .compact(); // Trả ra token đã được mã hóa
//    return token;
//}
    public String decodeToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        boolean isSuccess = false;
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            isSuccess = true;
        }
        catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return isSuccess;
    }
}
