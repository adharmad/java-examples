package com.example.javaexamples.functional;

// Use Runnable like a functional interface without a thread
public class TryRunnable1 {
    public static void main(String[] args) {
        Runnable runnable = doit("World1");
        System.out.println("Inside main : threadid = " + Thread.currentThread().getId());
        runnable.run();
    }

    public static Runnable doit(String name) {
        Runnable runner = () -> {
            System.out.println("Inside runnable : threadid = " + Thread.currentThread().getId());
            if (name.equals("World")) {
                System.out.println("Hello World!!!");
            } else {
                System.out.println("Hello, " + name);
                throw new RuntimeException("nonono");
            }
        };

        return runner;
    }
}
