package com.example.javaexamples.jni_examples.catchthrow;

class CatchThrow {
	private native void doit() throws IllegalArgumentException;
	public void callback() throws NullPointerException {
		throw new NullPointerException("CatchThrow.callback");
	}
	public static void main(String [] args) {
		CatchThrow c = new CatchThrow();
		try {
			c.doit();
		} catch (Exception e) {
			System.out.println("In Java:\n\t" + e);
		}
	}
	static {
		System.loadLibrary("CatchThrow");
	}	
}
