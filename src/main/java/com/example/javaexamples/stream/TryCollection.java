package com.example.javaexamples.stream;

import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/**
 * Averager example from here:
 * https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html
 */

public class TryCollection {

    public static class Averager implements IntConsumer {
        private int total = 0;
        private int count = 0;

        public Averager() {}

        public double average() {
            return count > 0 ? ((double) total) / count : 0;
        }

        @Override
        public void accept(int value) {
            total += value;
            count++;
        }

        public void combine(Averager other) {
            total += other.total;
            count += other.count;
        }
    }

    public static void main(String[] args) {

        Random random = new Random();
        List<Integer> intList =  random.ints().limit(10)
                .map( i -> Math.abs(i) % 100)
                .boxed().collect(Collectors.toList());

        System.out.println(intList);

        Averager averageCollector = intList.stream().collect(Averager::new, Averager::accept, Averager::combine);

        System.out.println("average = " + averageCollector.average());

    }
}
