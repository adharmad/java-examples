package com.example.javaexamples.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryRegex2 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\d*)(\\d*)((.*)\\s+(.*))?");
        Matcher matcher = pattern.matcher("-INC DBZL010B");
        if (matcher.matches()) {
            System.out.println("matches!!");
        } else {
            System.out.println("no match");
        }

        for (int i=1 ; i<matcher.groupCount() ; i++) {
            System.out.println(i + " " + matcher.group(i));
        }
    }
}
