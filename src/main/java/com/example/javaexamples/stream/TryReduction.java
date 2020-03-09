package com.example.javaexamples.stream;

import java.util.Random;

public class TryReduction {
    public static void main(String[] args) {
        // Sum of a list using stream reduction
        Random random = new Random();
        int sum = random.ints().limit(10)
                .map( i -> {
                    int j = Math.abs(i) % 100;
                    System.out.println(j);
                    return j;
                })
                .reduce(0, (a,b) -> a+b);
        System.out.println("Sum = " + sum);
    }
}
