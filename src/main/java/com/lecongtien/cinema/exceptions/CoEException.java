package com.lecongtien.cinema.exceptions;

import lombok.Data;

@Data
public class CoEException extends RuntimeException {
    private static final long serialVersionUID = 7393736796775438442L;
    private final String code;

    public CoEException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CoEException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
