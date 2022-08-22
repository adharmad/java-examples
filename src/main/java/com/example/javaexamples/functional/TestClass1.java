package com.example.javaexamples.functional;

import java.util.Map;

public class TestClass1 {

    private Map<String, Runnable> runners;

    public TestClass1() {
    }

    public void setup() {
        runners = Map.of(
                "cond1", () -> method1(),
                "cond2", () -> method2(),
                "cond3", () -> method3()
        ) ;
    }

    public void method1() {
        System.out.println("method1");
    }

    public void method2() {
        System.out.println("method2");
    }

    public void method3() {
        System.out.println("method3");
    }

    public Map<String, Runnable> getRunners() {
        return runners;
    }

    public void setRunners(Map<String, Runnable> runners) {
        this.runners = runners;
    }

    public static void main(String[] args) {
        TestClass1 testClass1 = new TestClass1();
        testClass1.setup();

        TestClass2 testClass2 = new TestClass2();
        testClass2.callOtherClassMethod(testClass1.getRunners(), "cond1");
        testClass2.callOtherClassMethod(testClass1.getRunners(), "cond2");
        testClass2.callOtherClassMethod(testClass1.getRunners(), "cond3");
    }
}
