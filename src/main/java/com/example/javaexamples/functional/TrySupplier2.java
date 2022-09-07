package com.example.javaexamples.functional;

import java.util.function.Supplier;

public class TrySupplier2 {
    public static void main(String[] args) {
        Supplier<String> supp = doSomething("World1");
        String said = supp.get();

        System.out.println(said);
    }

    public static Supplier<String> doSomething(String name) {
        Supplier<String> doer = () -> {
            String ret = "";
            if (name.equals("World")) {
                ret =  "I know you!";
            } else {
                throw new RuntimeException("I don't know you");
            }
            return ret;
        };

        return doer;
    }
}
