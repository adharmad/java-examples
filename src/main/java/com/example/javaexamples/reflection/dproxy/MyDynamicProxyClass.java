package com.example.javaexamples.reflection.dproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyDynamicProxyClass implements InvocationHandler {

	Object obj;

	public MyDynamicProxyClass(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method m, Object[] args)
			throws Throwable {
		try {
			// do something
		//} catch (InvocationTargetException e) {
		//	throw e.getTargetException();
		} catch (Exception e) {
			throw e;
		}
		return "done!";
	}

	static public Object newInstance(Object obj, Class[] interfaces) {
		return java.lang.reflect.Proxy.newProxyInstance(obj.getClass()
				.getClassLoader(), interfaces, new MyDynamicProxyClass(obj));
	}

	public static void main(String[] args) {
		MyProxyInterface foo = ProxyBuilder.newProxyInterface();
		System.out.println(foo.MyMethod());
	}
}
