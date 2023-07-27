package com.lecongtien.cinema.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filter the even numbers from the list
        List<Integer> evenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        // Map each number to its square
        List<Integer> squares = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // Combine the filter and map operations
        List<Integer> evenSquares = numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * num)
                .collect(Collectors.toList());
        System.out.println("Even squares: " + evenSquares);
    }
}
