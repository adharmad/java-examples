package com.example.javaexamples.filenio2;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by adharmad on 11/25/2016.
 */
public class WalkDir {
    public static void main(String[] args) throws Exception {

        String homeDir = System.getProperty("user.home");
        Path dir = Paths.get(homeDir + "/tmp");

        Files.walkFileTree(dir,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        if (file.toString().endsWith(".txt")) {
                            System.out.println(file.getFileName());
                        }

                        return FileVisitResult.CONTINUE;
                    }
                }
        );
    }
}
