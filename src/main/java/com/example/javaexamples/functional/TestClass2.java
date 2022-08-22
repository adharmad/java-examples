package com.example.javaexamples.functional;

import java.util.Map;

public class TestClass2 {
    public TestClass2() {
    }

    public void callOtherClassMethod(Map<String, Runnable> runners, String cond) {
        Runnable runnable = runners.get(cond);
        runnable.run();
    }
}
