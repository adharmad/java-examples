package com.example.javaexamples.io;

import java.io.*;
import java.util.Base64;
import java.util.zip.*;

public class CompressString {
    public static final String SOURCE_FILE = "/Users/amol/Repos/re-engineering/ionate-sources-analyzer/soteria-analyzer/src/main/java/io/ionate/soteria/analyzer/Application.java";
    public static void main(String[] args) throws Exception {

        String encodedString = compressAndEncodeFile();
        System.out.println(encodedString);
        String origFile = decodeAndUncompressFile(encodedString);

        System.out.println(origFile);

    }

    public static String compressAndEncodeFile() throws Exception {
        File fileToZip = new File(SOURCE_FILE);
        FileInputStream fis = new FileInputStream(fileToZip);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        fis.close();

        String inputStr = sb.toString();
        //System.out.println(inputStr);

        byte[] bytes = inputStr.getBytes();
        byte[] output = new byte[bytes.length];

        Deflater deflater = new Deflater();
        deflater.setInput(bytes);
        deflater.finish();
        int compressedDataLength = deflater.deflate(output);

        System.out.println(bytes.length + " " + compressedDataLength);

        byte[] res = new byte[compressedDataLength];
        System.arraycopy(output, 0, res, 0, compressedDataLength);

        String encodedString = Base64.getEncoder().encodeToString(res);
        return encodedString;
    }

    public static String decodeAndUncompressFile(String encodedString) throws Exception {

//        for (int i=0 ; i<decodedBytes.length ; i++) {
//            System.out.print(decodedBytes[i] + " ");
//        }
//        System.out.println();
//        System.out.println(encodedString.length());

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        byte[] result = new byte[decodedBytes.length * 10];

        System.out.println(decodedBytes.length);
        Inflater inflater = new Inflater();
        inflater.setInput(decodedBytes);
        int resultLength = inflater.inflate(result);
        inflater.end();

        System.out.println(resultLength);

        String decodedString = new String(result, 0, resultLength, "UTF-8");

        return decodedString;
    }
}
