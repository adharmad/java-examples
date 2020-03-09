/**
 * TryReflect.java
 * Created on Nov 15, 2004
 * @author Amol Dharmadhikari <amol@thortech.com>
 *
 */
package com.example.javaexamples.reflection;

import java.lang.reflect.Method;

public class TryReflect {
    public static void main(String[] args)
    {
		System.out.println("Entering Task1");
		Class c = null;
        Object o = null;
        Method m = null;
		
		try 
		{
			c = Class.forName("reflection.ThirdPartyClass"); 
            System.out.println("Class loaded");
            
            o = c.newInstance(); 
            System.out.println("Got new instance");

            m = c.getMethod("testMethod", 
                    new Class[]{String.class, String.class});
            System.out.println("Got method");

            Object o1 = m.invoke(o, new Object[] {"hello", "world"});
            System.out.println("Got method");
		} 
		catch (Exception e)
		{
			e.printStackTrace();	
		}
		
		System.out.println("Leaving Task1");
		
	}
}
