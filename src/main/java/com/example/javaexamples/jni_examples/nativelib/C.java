package com.example.javaexamples.jni_examples.nativelib;

public class C {
	public static native int atol(String str);

	public static void main(String [] args) {
		C c = new C();
		int i = c.atol(new String("142857"));
		System.out.println("i = " + i);
	}
	static {
		System.loadLibrary("atol");
	}
}
