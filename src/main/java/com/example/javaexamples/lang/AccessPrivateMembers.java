package com.example.javaexamples.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class AccessPrivateMembers {
	public static void main(String[] args) throws Exception {
		A a = new A("hello", 100);
		System.out.println(a);
		
		Class aCls = Class.forName("javasamples.lang.A");
		Constructor aCons = aCls.getConstructor(new Class[] {String.class, int.class});
		Object aObj = aCons.newInstance(new Object[]{"gogogo", 200});
		System.out.println(aObj);
		
		Field[] fields = aCls.getDeclaredFields();
		
		
		
	}
}
