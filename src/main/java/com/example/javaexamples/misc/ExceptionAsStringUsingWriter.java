package com.example.javaexamples.misc;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionAsStringUsingWriter {
	public static void main(String[] args) {
		try {
			ExceptionAsStringUsingWriter ex = new ExceptionAsStringUsingWriter();
			ex.throwException();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			String steStr = sw.toString();
			//e.printStackTrace();
			System.out.println(steStr);
		}
	}
	
	public void throwException() throws FooException {
		A a = new A();
		a.foo();
	}
	
	class A {
		public A() {
			
		}
		
		public void foo() throws FooException {
			throw new FooException(new BooException("hello"));
		}
	}
	
	class FooException extends Exception {
		public FooException() {
			super();
		}
		
		public FooException(String msg) {
			super(msg);
		}
		
		public FooException(Throwable t) {
			super(t);
		}
	}
	
	class BooException extends Throwable {
		public BooException() {
			super();
		}
		
		public BooException(String msg) {
			super(msg);
		}
	}
}
