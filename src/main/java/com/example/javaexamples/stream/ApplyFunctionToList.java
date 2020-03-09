package com.example.javaexamples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyFunctionToList {
    public static void main(String[] args) {
        List<String> animals = Arrays.asList("cat", "dog", "tiger", "lion");
        System.out.println(animals);

        // Another way of printing
        animals.forEach(System.out::println);

        // Uppercase each of the strings
        List<String> upperAnimals = animals.stream().map((s) -> s.toUpperCase()).collect(Collectors.toList());

        System.out.println(upperAnimals);
    }
}
