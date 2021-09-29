package com.example.javaexamples.apache;

import org.apache.commons.codec.binary.Hex;

public class TryHexString {
    public static void main(String[] args) {
        String s = "1234";
        String s1 = Hex.encodeHexString(s.getBytes());
        System.out.println(s1);
    }
}
