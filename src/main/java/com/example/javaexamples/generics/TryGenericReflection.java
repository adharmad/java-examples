package com.example.javaexamples.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TryGenericReflection {
    public static void main(String[] args) {
        Class cls = TopClass.class;
        Field[] fields = cls.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (field.getType() == List.class) {
                String genericClass = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName();
                System.out.println(field.getName() + " " + genericClass);
            } else {
                System.out.println(field.getName() + " " + field.getType());
            }
        });

        String[] arr = new String[10];
        System.out.println(arr.getClass().getComponentType().getTypeName());
        long[] arr1 = new long[10];
        System.out.println(arr1.getClass().getComponentType().getTypeName());
    }

}
