package com.example.javaexamples.stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Some examples from:
 * https://www.tutorialspoint.com/java8/java8_streams.htm
 */
public class TryBasicStreams {
    public static void main(String[] args) {
        // filter out non-empty strings
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println(strings);
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);

        // filter out strings more than size 5
        List<String> strings1 = Arrays.asList("this", "and", "the", "pronoun", "in", "out", "that");
        System.out.println(strings1);
        List<String> filtered1 = strings.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
        System.out.println(filtered1);

        // print 10 random numbers
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        // print 10 random numbers between 0 and 100
        random.ints().limit(10).map(i -> Math.abs(i) %100).forEach(System.out::println);

        // Filter out duplicates from list
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 2, 3, 4, 2, 3, 4, 5);
        System.out.println(intList);
        List<Integer> uniqueIntList = intList.stream().distinct().collect(Collectors.toList());
        System.out.println(uniqueIntList);

        // Get squares of numbers in a list
        List<Integer> squares = IntStream.range(1, 11).map(i -> i*i).boxed().collect(Collectors.toList());
        System.out.println(squares);

        // Get average of a list of 10 random numbers
        OptionalDouble average =
                random.ints().limit(10)
                        .map( i -> {
                            int j = Math.abs(i) % 100;
                            System.out.println(j);
                            return j;
                        })
                        .average();

        System.out.println("average = " + average);

    }
}
