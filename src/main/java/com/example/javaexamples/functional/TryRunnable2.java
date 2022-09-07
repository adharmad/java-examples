package com.example.javaexamples.functional;

// Use Runnable like a functional interface without a thread
public class TryRunnable2 {
    public static void main(String[] args) {
        Runnable runnable = getRunnable("World");
        System.out.println("Inside main : threadid = " + Thread.currentThread().getId());
        runnable.run();
    }

    public static Runnable getRunnable(String name) {
        Runnable runner = () -> doIt(name);
        return runner;
    }

    public static void doIt(String name) {
        System.out.println("Inside runnable : threadid = " + Thread.currentThread().getId());
        if (name.equals("World")) {
            System.out.println("Hello World!!!");
        } else {
            System.out.println("Hello, " + name);
            throw new RuntimeException("nonono");
        }
    }
}
