package com.example.javaexamples.functional;

import java.util.HashMap;
import java.util.Map;

// Use Runnable like a functional interface without a thread
public class TryRunnable4 {
    public static void main(String[] args) {
//        Runnable runnable = runThis("World1");
        System.out.println("Inside main : threadid = " + Thread.currentThread().getId());
        //runnable.run();
    }

    public static Runnable runThis(String var1, String var2, String var3) {
        Runnable runner = new Thread() {

            @Override
            public void run() {

            }
        };



        return runner;
    }
}
