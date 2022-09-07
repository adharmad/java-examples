package com.example.javaexamples.functional;

import java.util.Vector;

// Use Runnable like a functional interface without a thread
public class TryRunnable3 {
    public static void main(String[] args) {
        Vector<Runnable> v = new Vector();
        for (int i=0 ; i<10 ; i++) {
            final int ii = i;
            v.add(() -> doIt("blahblah" + ii));
        }

        System.out.println("Inside main : threadid = " + Thread.currentThread().getId());

        for (int i=0 ; i<v.size() ; i++) {
            Runnable runnable = v.get(i);
            runnable.run();
        }
    }

    public static void doIt(String name) {
        System.out.println(name);
        System.out.println("Inside runnable : threadid = " + Thread.currentThread().getId());
    }
}
