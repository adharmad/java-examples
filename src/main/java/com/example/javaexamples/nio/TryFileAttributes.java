package com.example.javaexamples.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by adharmad on 11/25/2016.
 */
public class TryFileAttributes {
    public static void main(String[] args) throws Exception {
        Path listing = Paths.get("d:/Dropbox/bin/gzip.exe");

        System.out.println(Files.readAttributes(listing, "*"));
    }
}
