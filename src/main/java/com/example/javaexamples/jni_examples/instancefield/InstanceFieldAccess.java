package com.example.javaexamples.jni_examples.instancefield;

class InstanceFieldAccess {
	private String s;

	private native void accessField();
	public static void main(String [] args) {
		InstanceFieldAccess c = new InstanceFieldAccess();
		c.s = "abc"; 
		System.out.println("Before acecssing: " + c.s);
		c.accessField();
		System.out.println("In Java:");
		System.out.println(" c.s = \"" + c.s + "\"");
	}
	static {
		System.loadLibrary("InstanceFieldAccess");
	}	
}
