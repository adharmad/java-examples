package com.example.javaexamples.io;

import java.io.*;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFile {
    public static void main(String[] args) throws IOException {
        String zipBytes = "UEsDBBQACAgIADxxR1YAAAAAAAAAAAAAAAAQAAAAQXBwbGljYXRpb24uamF2YZ1WTXObMBS88yt0xJ2O/oCbtGmbXJNJMtOzjJ+JaoGYJ8kJ7fi/V9gYBJIwlAPDx65WevtWULFsz3IgXFIuS6aBKmkwA0VZyUT9B3CdJLyoJOoJDK0AFVcaygzoU3/9AnjgGaznjMArELy0b0xRMKxpIbcg6Mv5bs4Imqm9op86qMScqgp5me+QFfAucU83Ump6V1WCZ0zbge4wNwWUWi1iPZuybAozQckkwmlG9E7VZfZqr+4/IDNaOgX9zQ6MGs2FhZeZQbQzoT9kUQnQbCPgwWiDffWC8AsmqczGzo9kgilFnMkS3ox3WiTx1kD+Jgmxh539wRaV7LitJnngAl4R4BcTe8Bm7vbF+NE6QnwGtnVI/W2I8N2esW5oTyitm8oW8UTdRF7EVEf03SziT46QWUfqEXsbfh4a4tyfXLUrVoPbEMFPB6kCgfGJXh8RPWyqE+XcBI7P6SwzP5/YgWPS0Shrsa2T+le9jbIXGhwdZ9LlKGuW1VH2tN8rm9wLUr9xRX1PyU00tQOWY+9NMLIdOuae5U3ndaDncWNh7VgRuyx1Mqkdf+iXpYVi2qF9jywjltGO5Zpj8cNsNqjjOaHfHg+AyLfg5vUg+ZagKdPQp4kwzNXKqqB8V+T+I4PqtK/3DeB9Mr58vR15f34+mpgtzKbg2qGnfses1td1+o5ZotOzrml43SUXicWac87S/kvQ7+hrUuNGXqYXiYEj2iq1v1O3owRM6aRD6Kr90gRXQpkQj7vU29JCvehvfKFO8lGTvRAedBZwyoEO7K6+rWVbytrbVtqS5KBTl+bvJJcdp610PRCpLbSg0mja/GFqUTqoBnBMjsk/UEsHCBeh22B4AgAAxQsAAFBLAQIUABQACAgIADxxR1YXodtgeAIAAMULAAAQAAAAAAAAAAAAAAAAAAAAAABBcHBsaWNhdGlvbi5qYXZhUEsFBgAAAAABAAEAPgAAALYCAAAAAA==";
        byte[] decodedBytes = Base64.getDecoder().decode(zipBytes);

        for (int i=0 ; i<decodedBytes.length ; i++) {
            System.out.print(decodedBytes[i] + " ");
        }
        System.out.println();

        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);

        ZipInputStream zis = new ZipInputStream(bis);
        ZipEntry zipEntry = zis.getNextEntry();

        String name = "";
        String contents = "";
        byte[] buffer = new byte[1024];


        if (zipEntry != null) {
            name = zipEntry.getName();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            while ((len = zis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            bos.close();

            contents = new String(bos.toByteArray());
        }

        bis.close();
        zis.close();

        System.out.println(name);
        System.out.println(contents);
        //System.out.println(encodedString.length());
    }
}
