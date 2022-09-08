package com.example.javaexamples.functional;

public class TryRunner {
    public static void main(String[] args){
        Runner runner1 = () -> System.out.print("Hello");
        Runner runner2 = () -> System.out.print("World");

        runner1.andThen(runner2).run();
        System.out.println();
        runner1.compose(runner2).run();

        System.out.println();

        Runner anotherRunner = () -> doSomething();
        anotherRunner.run();

        new TryRunner().runAgain();
    }

    public void runAgain() {
        Runner anotherRunner = () -> doSomethingElse();
        anotherRunner.run();
    }

    public static void doSomething() {
        System.out.println("something");
    }

    public void doSomethingElse() {
        System.out.println("somethingElse");
    }
}
