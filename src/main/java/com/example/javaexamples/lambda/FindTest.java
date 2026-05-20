package com.example.javaexamples.lambda;

import java.util.function.Predicate;

public class FindTest {
    public static <T> void findIt(T[] array, int index, Predicate<T> p, Runnable runThis, Runnable runAfter) {
        while (index < array.length) {
            System.out.println("Checking index = " + index);
            T predicateParam = array[index];
            if (p.test(predicateParam)) {
                System.out.println("Found at index = " + index);
                runThis.run();
                break;
            }
            index++;
        }

        runAfter.run();
    }
}
