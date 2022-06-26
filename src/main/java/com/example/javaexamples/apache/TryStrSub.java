package com.example.javaexamples.apache;

import org.apache.commons.lang.text.StrSubstitutor;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

public class TryStrSub {
    public static void main(String[] args) {
        String pattern = "---------------------------&#xD;&#xA;${foo} &#xD;&#xA;---------------------------&#xD;&#xA;${bar}&#xD;&#xA;${baz} ";
        pattern = pattern.replace("&#xD;&#xA;", System.getProperty("line.separator"));

        Map<String, String> map = Map.of(
                "foo", "hello",
                "bar", "world",
                "baz", "asdasd"
        ) ;

        StrSubstitutor strSubstitutor = new StrSubstitutor(map);
        String text = strSubstitutor.replace(pattern);
        System.out.println(text);
    }
}
