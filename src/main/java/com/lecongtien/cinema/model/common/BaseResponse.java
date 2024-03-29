package com.lecongtien.cinema.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer status;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(){
        return new BaseResponse<>(HttpStatus.OK.value(),null,null);
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(HttpStatus.OK.value(),null,data);
    }

    public static <T> BaseResponse<T> success(String message){
        return new BaseResponse<>(HttpStatus.OK.value(),message,null);
    }

    public static <T> BaseResponse<T> success(String message, T data){
        return new BaseResponse<>(HttpStatus.OK.value(),message,data);
    }

    public static <T> BaseResponse<T> badRequest(T data){
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),null,data);
    }

    public static <T> BaseResponse<T> badRequest(String message){
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),message,null);
    }

    public static <T> BaseResponse<T> badRequest(String message, T data){
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(),message,data);
    }
    public static <T> BaseResponse<T> fail(String message){
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),message,null);
    }

    public static <T> BaseResponse<T> unAuthorized(String message){
        return new BaseResponse<>(HttpStatus.UNAUTHORIZED.value(),message,null);
    }
}
