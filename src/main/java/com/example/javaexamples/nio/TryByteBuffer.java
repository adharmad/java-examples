package com.example.javaexamples.nio;

import java.nio.ByteBuffer;

public class TryByteBuffer {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(999999);
        byte[] b = bb.array();
        for (int i=0 ; i<b.length ; i++) {
            System.out.println(b[i]);
        }

        System.out.println(ByteBuffer.wrap(bb.array()).getInt());
        //System.out.println(bb.getInt());
    }
}
