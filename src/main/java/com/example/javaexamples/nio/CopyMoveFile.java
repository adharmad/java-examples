package com.example.javaexamples.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by adharmad on 11/25/2016.
 */
public class CopyMoveFile {
    public static void main(String[] args) throws Exception {

        String homeDir = System.getProperty("user.home");

        Path source = Paths.get(homeDir  + "/tmp/test.txt");
        Path target = Paths.get(homeDir  + "/tmp/test-copy.txt");
        Path target1 = Paths.get(homeDir  + "/tmp/test-move.txt");

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("FileName: " + target.getFileName());
        System.out.println("name count: " + target.getNameCount());
        System.out.println("URI: " + target.toUri());

        // now try moving the file
        Files.move(source, target1, StandardCopyOption.ATOMIC_MOVE);

        System.out.println("FileName: " + target1.getFileName());
        System.out.println("name count: " + target1.getNameCount());
        System.out.println("URI: " + target1.toUri());
    }
}
