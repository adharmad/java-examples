package com.example.javaexamples.lambda;

import java.util.function.Predicate;

public abstract class CustomPredicate implements Predicate<User> {

    private String condition;

    public CustomPredicate(String condition) {
        this.condition = condition;
    }
}
