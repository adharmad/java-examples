package com.example.javaexamples.nio;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class WriteLargeFileNio {
    public static void main(String[] args) throws Exception {
        int number_of_lines = 500000;
        FileChannel rwChannel = new RandomAccessFile("textfile.txt", "rw").getChannel();
        ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, 80 * number_of_lines);
        for (int i = 0; i < number_of_lines; i++)
        {
            String randStr = RandomStringUtils.randomAlphabetic(79) + "\n";
            byte[] buffer = randStr.getBytes(StandardCharsets.UTF_8);
            wrBuf.put(buffer);
        }
        rwChannel.close();
    }
}
