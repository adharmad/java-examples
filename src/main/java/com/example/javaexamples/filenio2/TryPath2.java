package com.example.javaexamples.filenio2;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by adharmad on 11/25/2016.
 */
public class TryPath2 {
        public static void main(String[] args) {
            File f = new File("../test.txt");
            Path filePath = f.toPath();

            filePath.toAbsolutePath();

            System.out.println("FileName: " + filePath.getFileName());
            System.out.println("Root: " + filePath.toUri());

        }
}
