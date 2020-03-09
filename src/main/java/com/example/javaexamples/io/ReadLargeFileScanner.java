package com.example.javaexamples.io;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */

// Too much variation in memory usage of this from 159MB to 4 MB!
public class ReadLargeFileScanner {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/adharmad/Dropbox/dev/playground/java-examples/sources.200MB";

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(fileName);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // do something with the line
                if (line.startsWith("NEVERNEVERNEVER")) {
                    System.out.println("hello?!");
                }
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=(afterUsedMem-beforeUsedMem) / 1024 / 1024;

        System.out.println("Used memory " + actualMemUsed + " MB");

    }
}
