package com.example.javaexamples.string;

public class Format1 {
    public static void main(String[] args) {
        String toFormat = "Hello world %s! This is a test %s.";
        String formatted = String.format(toFormat, "John", "Doe");
        System.out.println(formatted);
    }
}
