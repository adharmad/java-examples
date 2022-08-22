package com.example.javaexamples.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryRegex1 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^EXEC\\s+SQL\\s+INCLUDE\\s+(.*)\\s+END-EXEC\\.$");
        Matcher matcher = pattern.matcher("EXEC SQL INCLUDE TESTCPY2 END-EXEC.");
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
