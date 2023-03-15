package com.lecongtien.cinema;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TestFUnction {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

        String dateInString = "12/1/2013 10:11:59";
        DateTime dateTime = DateTime.parse(dateInString, formatter);
        System.out.println("date " + dateTime);
    }
}
