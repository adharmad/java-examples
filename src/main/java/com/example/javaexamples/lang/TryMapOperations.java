package com.example.javaexamples.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TryMapOperations {
    public static void main(String[] args) {
        Map<String, String> hm = new HashMap<>();
        hm.put("a1", "b1");
        hm.put("a2", "b2");
        hm.put("a3", "b3");

        Set<String> keys = new HashSet<>();
        keys.add("a1");
        keys.add("a3");

        System.out.println(hm);
        hm.keySet().retainAll(keys);
        System.out.println(hm);
    }
}
