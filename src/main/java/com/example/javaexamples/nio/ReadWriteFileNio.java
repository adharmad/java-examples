package com.example.javaexamples.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class ReadWriteFileNio {
    public static void main(String[] args) throws Exception {
        //String fileName = "/Users/adharmad/Dropbox/dev/playground/java-examples/test.txt";
        String fileName = "d:/Dropbox/dev/playground/java-examples/test.txt";
        Path path = Paths.get(fileName);
        SeekableByteChannel byteChannel = Files.newByteChannel(path);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);

        ArrayList outBuf = new ArrayList();
        Charset charset = Charset.forName("US-ASCII");

        StringBuffer buf = new StringBuffer();

        while (byteChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            for (int i = 0; i < byteBuffer.limit(); i++)
            {
                char ch = ((char)charset.decode(byteBuffer).get());
                if(ch == '\n'){
                    System.out.print(buf+"[EOL]");
                    outBuf.add(buf.toString());
                    buf = new StringBuffer();
                }else{
                    buf.append(ch);
                }
            }

            byteBuffer.clear();
        }

        byteChannel.close();

        System.out.println(String.join("\n", outBuf));
    }
}
