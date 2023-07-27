package com.lecongtien.cinema.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    SUCCESS("Success"),
    FAILED("Failed");
    private  final String message;

    public String getMessage() {return message;}

    public static Status getInstance(final String browserName){
        final String[] array = browserName.split("\\s+");
        final StringBuilder key = new StringBuilder();
        for (String word: array){
            key.append(word.toUpperCase());
        }
        return Enum.valueOf(Status.class, key.toString());
    }
}
