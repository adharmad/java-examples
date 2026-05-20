package com.example.javaexamples.sort;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private String age;

    public Person(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
