package com.example.javaexamples.misc;

public class ExceptionAsString {
	public static void main(String[] args) {
		try {
			ExceptionAsString ex = new ExceptionAsString();
			ex.throwException();
		} catch (Exception e) {
			//e.printStackTrace();
			StackTraceElement[] ste = e.getStackTrace();
			StringBuffer sbuf = new StringBuffer();
			sbuf.append(e.getClass().getName() + ": " + e.getMessage() + "\n");
			for (int i=0 ; i<ste.length ; i++) {
				sbuf.append("\t" + ste[i].toString() + "\n");
			}
			
			String steStr = sbuf.toString();
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
			throw new FooException("asdasd");
		}
	}
	
	class FooException extends Exception {
		public FooException() {
			super();
		}
		
		public FooException(String msg) {
			super(msg);
		}
	}
 }
