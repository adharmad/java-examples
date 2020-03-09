package com.example.javaexamples.lang;

import java.io.*;

public class ReadFromStdin {
    public static void main(String[] args) throws Exception {
	InputStreamReader in = new InputStreamReader(System.in);
	BufferedReader bin = new BufferedReader(in);

	String input = "";
	
	while (!input.equalsIgnoreCase("quit")) {
	    System.out.println(input);
	    input = bin.readLine();
	}
	
    }
}
