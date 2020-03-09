package com.example.javaexamples.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */

// Naive - will use large amount of memory
public class ReadLargeFileNio {
    public static void main(String[] args) {

        Path wiki_path = Paths.get("/Users/adharmad/Dropbox/dev/playground/java-examples/sources.200MB");
        Charset charset = Charset.forName("ISO-8859-1");
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        try {
            List<String> lines = Files.readAllLines(wiki_path, charset);

            for (String line : lines) {
                if (line.startsWith("NEVERNEVERNEVER")) {
                    System.out.println("hello?!");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=(afterUsedMem-beforeUsedMem) / 1024 / 1024;

        System.out.println("Used memory " + actualMemUsed + " MB");


    }
}
