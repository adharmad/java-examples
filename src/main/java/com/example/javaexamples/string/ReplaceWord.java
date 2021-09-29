package com.example.javaexamples.string;

public class ReplaceWord {
    public static void main(String[] args) {
        String str1 = "SELECT FOO, :ID_PADRE, BAR FROM T";
        String str2 = "SELECT FOO, :ID_PADREMOD, BAR FROM T";

        System.out.println(replaceWord(str1, ":ID_PADRE", "237"));
        System.out.println(replaceWord(str2, ":ID_PADRE", "237"));
    }

    public static String replaceWord(String str, String word1, String word2) {
        String s = str.replaceAll(word1 + "\\b", word2);
        return s;
    }
}
