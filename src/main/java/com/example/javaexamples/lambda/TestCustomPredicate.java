package com.example.javaexamples.lambda;

public class TestCustomPredicate {

    public static void main(String[] args) {
        CustomPredicate customPredicate = new CustomPredicate("this is a test") {
            @Override
            public boolean test(User user) {
                return user.getAge() > 100;
            }
        };
    }
}
