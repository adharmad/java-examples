package com.example.javaexamples.misc;

public class GetCurrentClassAndMethod {
	public static void main(String args[]) {
		new GetCurrentClassAndMethod().myMethod();
	}

	public void myMethod() {
		System.out.println(Thread.currentThread().getStackTrace()[1]
		.getClassName() + " "
				+ Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " " + Thread.currentThread().getStackTrace()[1].getLineNumber());

		// StackTraceElement[] st = Thread.currentThread().getStackTrace();

		// for (StackTraceElement ste : st) {
		// System.out.println(ste.getClassName() + " " + ste.getMethodName());
		// }
	}

}
