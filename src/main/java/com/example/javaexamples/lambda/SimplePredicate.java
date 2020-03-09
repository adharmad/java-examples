package com.example.javaexamples.lambda;

import java.util.function.Predicate;

public class SimplePredicate {
    public static void main(String[] args) {
        Predicate<Integer> evenNuumbers = i -> i%2 == 0;
        Predicate<Integer> oddNuumbers = i -> i%2 == 1;
        Predicate<Integer> multiplesOfThree = i -> i%3 == 0;
        Predicate<Integer> multiplesOfFive = i -> i%5 == 0;
        Predicate<Integer> multiplesOfThreeAndFive = i -> i%5 == 0 && i%3 == 0;

        for (int i=0 ; i<=100 ; i++) {
            if (evenNuumbers.test(i)) {
                System.out.println(i + " is an even number");
            }

            if (oddNuumbers.test(i)) {
                System.out.println(i + " is an odd number");
            }

            if (multiplesOfThree.test(i)) {
                System.out.println(i + " is a multiple of 3");
            }

            if (multiplesOfFive.test(i)) {
                System.out.println(i + " is a multiple of 5");
            }

            if (multiplesOfThreeAndFive.test(i)) {
                System.out.println(i + " is a multiple of 3 and 5");
            }
        }
    }
}
