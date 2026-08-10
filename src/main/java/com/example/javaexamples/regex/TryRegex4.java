package com.example.javaexamples.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryRegex4 {
    public static void main(String[] args) {
        String[] values = {
                "OBJ1.SUBOBJ1.AOBJ1.CHILDOBJ1.CHILDOBJ2.CHILDOBJ3.CHILDOBJ4.VAR1.YES-VALUE",
                "OBJ1.SUBOBJ1.BOBJ1.CHILDOBJ1.CHILDOBJ2.CHILDOBJ3.CHILDOBJ4.VAR1.YES-VALUE",
        };
        Pattern pattern = Pattern.compile(".*BOBJ1.*YES-VALUE$");

        for (String val : values) {
            Matcher matcher = pattern.matcher(val);

            if (matcher.matches()) {
                System.out.println(val + " matches!!");
            } else {
                System.out.println(val + " does not match!!");
            }
        }
    }
}
