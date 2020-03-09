package com.example.javaexamples.misc;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageFilterTest {

    public static void main(String[] args) {
        //List<String> ignoreLst = Arrays.asList("getDeParaNDSGNDIResponse");
        List<String> ignoreLst = Arrays.asList("");

        String[] patterns = {
                "/body/getDeParaNDSGNDIResponse/getDeParaNDSGNDIResponse/credencialNDS = '0'",
                "(/body/getDeParaNDSGNDIResponse/getDeParaNDSGNDIResponse/credencialNDS = '1')",
                "(/body/getDeParaNDSGNDIResponse/getDeParaNDSGNDIResponse/credencialNDS) = '2'",
                "((/body/getDeParaNDSGNDIResponse/getDeParaNDSGNDIResponse/credencialNDS) = (/body/getDeParaNDSGNDIResponse/getDeParaNDSGNDIResponse/credencialIMS))",
                "(/context/correlation/codigoPerfil) = 'BENEFICIARIO'"
        };

        for (String pattern : patterns) {
            String pattern1 = modifyMessageFilterPattern(pattern, ignoreLst);
            System.out.println(pattern1);
        }

//        String[] toks = StringUtils.splitByWholeSeparator("ben and jerry", "and");
//        System.out.println(Arrays.toString(toks));
//        boolean a = true;
//        boolean b = false;
//        //a = a && b;
//        a &= b;
//
//        System.out.println(a);
    }

    public static String modifyMessageFilterPattern(
            String pattern, List<String> classNameSequenceToIgnore) {

        String retPattern = pattern;
        List<String> patternsToRemove = new ArrayList<String>();
        patternsToRemove.add("/context/correlation");
        patternsToRemove.add("/context/shared");

        // Split on '/'
        String[] splitStr = retPattern.split("/");

        // If 'body' element exists followed by word and one of the classes to ignore, add to list to
        // strip.
        for (int i = 0; i < splitStr.length; i++) {

            if (splitStr[i].equalsIgnoreCase("body")) {
                boolean containsSequence =
                        classNameSequenceToIgnore.stream().anyMatch(splitStr[i + 2]::equalsIgnoreCase);
                if (containsSequence) {
                    patternsToRemove.add("/body/" + splitStr[i + 1] + "/" + splitStr[i + 2]);
                }
            }
        }

        for (String patternToRemove : patternsToRemove) {
            retPattern = retPattern.replaceAll(patternToRemove, "");
        }
        return retPattern;
    }
}
