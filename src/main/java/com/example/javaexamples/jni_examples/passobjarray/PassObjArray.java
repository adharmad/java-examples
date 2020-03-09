package com.example.javaexamples.jni_examples.passobjarray;

class PassObjArray {
	public static native void passArray(String[] s);
	public PassObjArray() { }
	public static void main(String[] args) {
		passArray(args);
	}
	static {
		System.loadLibrary("PassObjArray");
	}
}
