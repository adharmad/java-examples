package com.example.javaexamples.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TryClsLoad {
	public static void main(String[] args) {
		try {
			String var1 = "true1";
			Object objCons = null;

			Class targetCls = Class.forName("lambda.clsload.TestClass");

			System.out.println("Target Class = " + targetCls.getName());

			// Initialize method
			Class[] methodParamTypes = new Class[] { String.class };
			Object[] methodParams = new Object[] { var1 };
			Method method = targetCls.getMethod("method1", methodParamTypes);

			// Initialize constructor
			Object[] constArgs = new Object[] {};
			Class[] consParamTypes = new Class[] {};
			Constructor cons = targetCls.getConstructor(consParamTypes);
			objCons = cons.newInstance(constArgs);

			// Invoke method
			Object retVal = method.invoke(objCons, methodParams);
			System.out.println(retVal);
			
			//TestClass tc = new TestClass();
			//tc.method1("true1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Message = " + e.getMessage());
			System.out.println("Cause = " + e.getCause());
		}

	}
}
