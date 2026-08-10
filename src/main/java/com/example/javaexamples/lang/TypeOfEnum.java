package com.example.javaexamples.lang;

import java.util.Arrays;
import java.util.List;

import static com.example.javaexamples.lang.TypeOfEnum.TEST1.*;

public class TypeOfEnum {
    public enum TEST1 {
        VAL1,
        VAL2,
        VAL3;
    }

    public static void main(String[] args) {
        List lst = Arrays.asList(VAL1, VAL2, 123, "Hello", 4.56, VAL3);

        for (Object elem : lst) {
            System.out.println(elem.getClass().getName());
        }
    }
}
