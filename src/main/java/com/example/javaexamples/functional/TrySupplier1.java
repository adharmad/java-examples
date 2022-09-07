package com.example.javaexamples.functional;

import java.util.function.Supplier;

public class TrySupplier1 {
    public static void main(String[] args) {
        Supplier<String> supp = sayHello("World");
        String said = supp.get();

        System.out.println(said);
    }

    public static Supplier<String> sayHello(String name) {
        Supplier<String> helloSayer = () -> "Hello " + name + "!";
        return helloSayer;
    }
}
