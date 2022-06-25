package com.example.javaexamples.tok;

import java.util.ArrayList;
import java.util.List;


public class Strtok1 {
    public static void main(String[] args) {
        String pattern1 = "---------------------------&#xD;&#xA;${CURRENT_TIMESTAMP} &#xD;&#xA;---------------------------&#xD;&#xA;${Environment.Variables}&#xD;&#xA;${ExceptionList} ";
        String[] split = pattern1.split("\\$\\{|\\}");
        System.out.println("hello");

        List<String> patterns = new ArrayList<>();
        List<String> overall = new ArrayList<>();
        for (int i=0 ; i<split.length ; i++) {
            if (i%2 == 0) {
                overall.add(split[i]);
            } else {
                patterns.add(split[i]);
            }
        }

        StringBuffer sbuf = new StringBuffer();
        for (int i=0 ; i<overall.size() ; i++) {
            sbuf.append(overall.get(i));
            if (i< overall.size()-1) {
                sbuf.append("{" + i + "}");
            }
        }

        System.out.println(patterns);
        System.out.println(sbuf.toString());
    }
}
