package com.example.javaexamples.io;

import java.io.*;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    public static final String SOURCE_FILE = "/Users/amol/Repos/re-engineering/ionate-sources-analyzer/soteria-analyzer/src/main/java/io/ionate/soteria/analyzer/Application.java";
    public static void main(String[] args) throws Exception {

        String encodedString = compressAndEncodeFile();
        System.out.println(encodedString);
        String origFile = decodeAndUncompressFile(encodedString);

    }

    public static String compressAndEncodeFile() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(bos);

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

        byte[] bytes = sb.toString().getBytes();

        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipEntry.setComment("test!!");
        zipOut.putNextEntry(zipEntry);

        zipOut.write(bytes, 0, bytes.length);
        zipOut.closeEntry();
        zipOut.close();

//        zipOut.flush();
//        zipOut.finish();

//        for (int i=0 ; i<bos.toByteArray().length ; i++) {
//            System.out.print(bos.toByteArray()[i] + " ");
//        }
//        System.out.println();

        String encodedString = Base64.getEncoder().encodeToString(bos.toByteArray());

        bos.close();
        return encodedString;
    }

    public static String decodeAndUncompressFile(String encodedString) throws Exception {

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//        for (int i=0 ; i<decodedBytes.length ; i++) {
//            System.out.print(decodedBytes[i] + " ");
//        }
//        System.out.println();
//        System.out.println(encodedString.length());

        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
        ZipInputStream zis = new ZipInputStream(bis);

        ZipEntry entry;
        while((entry = zis.getNextEntry())!=null) {
            System.out.println(entry.getName());
            System.out.println(entry.getSize());
            System.out.println(entry.getCompressedSize());
            System.out.println(entry.getComment());

            // Once we get the entry from the stream, the stream is
        }

        return "";
    }
}
