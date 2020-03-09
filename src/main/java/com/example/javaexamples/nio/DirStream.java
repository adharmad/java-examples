package com.example.javaexamples.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DirStream {
    public static void main(String[] args) throws Exception {
        //String dir = "d:/Dropbox/tmp";
        Path dir = Paths.get("d:/Dropbox/tmp");

        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }

        //result.stream().forEach(System.out::println);
        for (Path p : result) {
            System.out.println(p.toAbsolutePath().toString());
        }
    }
}
