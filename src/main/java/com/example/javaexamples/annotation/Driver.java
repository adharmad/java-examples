package com.example.javaexamples.annotation;

public class Driver {
    public static void main(String[] args) {
        TestAnnotation testAnnotation = A.class.getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation.testValue());

        TestAnnotation testAnnotation1 = B.class.getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation1.testValue());

        TestAnnotation testAnnotation2 = C.class.getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation2.testValue());
    }
}
