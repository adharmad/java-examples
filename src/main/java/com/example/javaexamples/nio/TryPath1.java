package com.example.javaexamples.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by adharmad on 11/25/2016.
 */
public class TryPath1 {
    public static void main(String[] args) {
        Path listing = Paths.get("d:/Dropbox/bin/gzip.exe");

        System.out.println("FileName: " + listing.getFileName());
        System.out.println("FS: " + listing.getFileSystem());
        System.out.println("name count: " + listing.getNameCount());
        System.out.println("Parent: " + listing.getParent());
        System.out.println("Root: " + listing.getRoot());
        System.out.println("URI: " + listing.toUri());
        //System.out.println("Root: " + listing.toAbsolutePath());
    }
}
