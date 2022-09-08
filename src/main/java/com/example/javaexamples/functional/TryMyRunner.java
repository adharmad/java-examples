package com.example.javaexamples.functional;

public class TryMyRunner {
    public static void main(String[] args){
        MyRunner myRunner = () -> doSomething();
        myRunner.invoke();

        TryMyRunner tryMyRunner = new TryMyRunner();
        tryMyRunner.runAgain();
    }

    public static void doSomething() {
        System.out.println("something");
        throw new RuntimeException("hello!");
    }

    public void runAgain() {
        MyRunner anotherRunner = () -> doSomethingElse();
        anotherRunner.invoke();
    }

    public void callme() {
        System.out.println("callme");
    }

    public void doSomethingElse() {
        System.out.println("somethingElse");
    }
}
