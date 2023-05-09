package com.example.javaexamples.io;

import java.io.*;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {

    public static void main(String[] args) throws IOException {
        String sourceFile = "/Users/amol/Repos/re-engineering/ionate-sources-analyzer/src/main/java/io/ionate/sources/analyzer/Application.java";
        //String sourceFile = "/Users/amol/Repos/re-engineering/cobol-transpiler/src/main/java/com/ionate/stackconvertor/transpiler/csharp/visitor/CSharpSentenceStatementVisitor.java";
        //FileOutputStream fos = new FileOutputStream("compressed.zip");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(bos);

        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.flush();
        zipOut.finish();

        for (int i=0 ; i<bos.toByteArray().length ; i++) {
            System.out.print(bos.toByteArray()[i] + " ");
        }

        System.out.println();

        String encodedString = Base64.getEncoder().encodeToString(bos.toByteArray());
        zipOut.close();
        fis.close();
        bos.close();

        System.out.println(encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        for (int i=0 ; i<decodedBytes.length ; i++) {
            System.out.print(decodedBytes[i] + " ");
        }
        System.out.println();
        //System.out.println(encodedString.length());
    }
}
