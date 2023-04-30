package com.example.javaexamples.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryRegex3 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[A-Z0-9\\-]+[.]");
        Matcher matcher = pattern.matcher("1234-PARA-NAME-FOO.");
        if (matcher.matches()) {
            System.out.println("matches!!");
        } else {
            System.out.println("no match");
        }

        for (int i=0 ; i<matcher.groupCount() ; i++) {
            System.out.println(matcher.group(i));
        }
    }
}
