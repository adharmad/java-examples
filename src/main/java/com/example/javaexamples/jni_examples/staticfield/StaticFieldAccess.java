package com.example.javaexamples.jni_examples.staticfield;

class StaticFieldAccess {
	private static int si;

	private native void accessField();
	public static void main(String [] args) {
		StaticFieldAccess c = new StaticFieldAccess();
		StaticFieldAccess.si = 999; 
		System.out.println("Before acecssing: " + StaticFieldAccess.si);
		c.accessField();
		System.out.println("In Java:");
		System.out.println(" StaticFieldAccess.s = " + si);
	}
	static {
		System.loadLibrary("StaticFieldAccess");
	}	
}
