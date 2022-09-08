package com.example.javaexamples.functional;

@FunctionalInterface
public interface MyRunner {
    void run();

    default void invoke() {
        try {
            this.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
