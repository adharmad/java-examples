package com.example.javaexamples.io;

import java.io.*;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class ReadLargeFileBR {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/adharmad/Dropbox/dev/playground/java-examples/sources.200MB";
        InputStream inputStream = new FileInputStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        // This may throw OOM with large files
        //BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        while ((line = br.readLine()) != null) {
            // do something with the line
            if (line.startsWith("NEVERNEVERNEVER")) {
                System.out.println("hello?!");
            }
        }

        br.close();

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=(afterUsedMem-beforeUsedMem) / 1024 / 1024;

        System.out.println("Used memory " + actualMemUsed + " MB");
    }
}
