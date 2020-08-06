package com.example.javaexamples.lang;

import java.util.Optional;

public class TryOptional1 {
    public static void main(String[] args) {
        String s1= "abc";
        String s2= null;

        String opt1 = Optional.of(s1).orElse("def");
        String opt2 = Optional.ofNullable(s2).orElse("def");

        System.out.println(opt1);
        System.out.println(opt2);
    }
}
