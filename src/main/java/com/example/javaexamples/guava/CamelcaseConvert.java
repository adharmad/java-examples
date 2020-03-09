package com.example.javaexamples.guava;

import com.google.common.base.CaseFormat;

import java.util.Arrays;

public class CamelcaseConvert {
    public static void main(String[] args) {
        String[] strings = {
                "This_is_a_string",
                "this_is_a_string",
                "p_cursor",
                "p_Cursor"
        };

        Arrays.stream(strings).forEach(str ->
                System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str))
        );
    }
}
