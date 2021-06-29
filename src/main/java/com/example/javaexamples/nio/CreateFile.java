package com.example.javaexamples.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Created by adharmad on 11/25/2016.
 */
public class CreateFile {
    public static void main(String[] args) throws Exception {

        String homeDir = System.getProperty("user.home");
        String target = homeDir + "/tmp/stuff.txt";

        Path toCreate = Paths.get(target);
        Set<PosixFilePermission> attrs = PosixFilePermissions.fromString("rw-------");

        //Path file = Files.createFile(toCreate, PosixFilePermissions.asFileAttribute(attrs));
        Path file = Files.createFile(toCreate);

        Path listing = Paths.get(target);

        System.out.println("FileName: " + listing.getFileName());
        System.out.println("FS: " + listing.getFileSystem());
        System.out.println("name count: " + listing.getNameCount());
        System.out.println("Parent: " + listing.getParent());
        System.out.println("Root: " + listing.getRoot());
        System.out.println("URI: " + listing.toUri());

        // now delete it
        Path toDelete = Paths.get(target);
        //Files.delete(toDelete);

    }
}
