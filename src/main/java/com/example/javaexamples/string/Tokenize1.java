package com.example.javaexamples.string;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tokenize1 {
    public static void main(String[] args) {
        String str1 = "/pCursor/origemcontrato = '3'";
        String str2 = "string-length(/pCursor/credencial) < 18";

        String[] arr1 = str1.split("/");
//        for (String s : arr1) {
//            System.out.println(s);
//        }

        List<String> lst1 = Arrays.asList(arr1);
        lst1.set(1, "fooPCursor");
        String sstr1 = lst1.stream().collect(Collectors.joining("/"));
        System.out.println(str1);
        System.out.println(sstr1);

        String[] arr2 = str2.split("/");
//        for (String s : arr2) {
//            System.out.println(s);
//        }

        List<String> lst2 = Arrays.asList(arr2);
        lst2.set(1, "booPCursor");
        String sstr2 = lst2.stream().collect(Collectors.joining("/"));
        System.out.println(str2);
        System.out.println(sstr2);
    }
}
