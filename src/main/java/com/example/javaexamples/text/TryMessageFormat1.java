package com.example.javaexamples.text;

import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

public class TryMessageFormat1 {
    public static void main(String[] args) {
        String pattern = "---------------------------&#xD;&#xA;{0} &#xD;&#xA;---------------------------&#xD;&#xA;{1}&#xD;&#xA;{2} ";
        pattern = pattern.replace("&#xD;&#xA;", System.getProperty("line.separator"));
        String text = MessageFormat.format(pattern, new Date().toString(), "var1", "java.Lang.Exception");
        System.out.println(text);
    }
}
