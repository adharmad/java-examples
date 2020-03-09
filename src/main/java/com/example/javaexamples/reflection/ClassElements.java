package com.example.javaexamples.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassElements {
    public static void main(String[] args) {
        Class threadClass = Thread.class;

        System.out.println("Fields: ");
        Field[] fields = threadClass.getFields();
        for (Field field : fields) {
            System.out.println("\t" + field);
        }

        System.out.println("Enum constants: ");
        Object[] objects = threadClass.getEnumConstants();
        if (objects != null) {
            for (Object obj : objects) {
                System.out.println("\t" + obj);
            }
        }

        System.out.println("Constructors: ");
        Constructor[] constructors = threadClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("\t" + constructor);
        }

        System.out.println("Methods: ");
        Method[] methods = threadClass.getMethods();
        for (Method method : methods) {
            System.out.println("\t" + method);
        }
    }
}
