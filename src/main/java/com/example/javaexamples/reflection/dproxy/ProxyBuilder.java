package com.example.javaexamples.reflection.dproxy;

public class ProxyBuilder {
	public static MyProxyInterface newProxyInterface() {
		MyProxyInterface foo = (MyProxyInterface) MyDynamicProxyClass
				.newInstance(new Object(), new Class[] { MyProxyInterface.class });
		
		return foo;
	}

}
