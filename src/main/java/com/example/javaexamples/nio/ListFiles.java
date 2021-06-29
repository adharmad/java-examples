package com.example.javaexamples.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by adharmad on 11/25/2016.
 */
public class ListFiles {
    public static void main(String[] args) {

        String homeDir = System.getProperty("user.home");
        Path dir = Paths.get(homeDir + "/tmp");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
