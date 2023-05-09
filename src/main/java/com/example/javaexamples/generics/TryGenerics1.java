package com.example.javaexamples.generics;

public class TryGenerics1 {
    public static void main(String[] args) {
        TryGenerics1 tryGenerics1 = new TryGenerics1();
        Object ret = tryGenerics1.<String>returnSomething("int");
        if ((int)ret == 10) {
            System.out.println("hello");
        }
    }

    public <T> T returnSomething(String param) {
        T ret = null;
        if (param.equalsIgnoreCase("string")) {
            ret = (T)"string1";
        } else if (param.equalsIgnoreCase("int")) {
            ret = (T)(new Integer(10));
        } else {
            ret = (T)new Object();
        }

        return ret;
    }
}
