package com.example.javaexamples.net;

import java.net.URI;
import java.net.URL;

public class ParseURL {
    public static void main(String[] args) throws Exception {
        String testurl = "http://172.16.50.238:4000/renapo/ws/ConsultaCurpDetalleService";
        URL url = new URL(testurl);
        URI uri = new URI(testurl);

        System.out.println(uri.getPath());


    }
}
