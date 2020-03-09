package com.example.javaexamples.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class TrySummaryStatistics {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(23, 55, 6, 9, 78, 99, 37, 64);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("Highest number in List : " + stats.getMax());
        System.out.println("Lowest number in List : " + stats.getMin());
        System.out.println("Sum of all numbers : " + stats.getSum());
        System.out.println("Average of all numbers : " + stats.getAverage());
    }
}
