package com.example.javaexamples.io;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class ReadWriteFileBRBW {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/adharmad/Dropbox/dev/playground/java-examples/test.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        String line = "";
        ArrayList outBuf = new ArrayList();
        int count = 0;

        while ((line = br.readLine()) != null) {
            // change every line pre-pending it with a count
            String outLine = (++count) + " " + line;
            //System.out.println(outLine);
            outBuf.add(outLine);

        }

        System.out.println(outBuf.toString());

        String outStr = String.join("\n", outBuf);
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        bw.write(outStr);
        bw.flush();

        br.close();
        bw.close();
    }
}
