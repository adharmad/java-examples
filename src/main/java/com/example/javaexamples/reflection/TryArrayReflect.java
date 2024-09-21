/**
 * TryReflect.java
 * Created on Nov 15, 2004
 * @author Amol Dharmadhikari <amol@thortech.com>
 *
 */
package com.example.javaexamples.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

public class TryArrayReflect {
    public static void main(String[] args) throws Exception {
		Class cls = BasicObj.class;
		Field[] fields = cls.getDeclaredFields();
		Arrays.stream(fields).forEach(field -> {
			if (field.getType().isArray()) {
				Class arrayType = field.getType().getComponentType();
				System.out.println(field.getName() + " " + arrayType);
			} else {
				System.out.println(field.getName() + " " + field.getType());
			}
		});
		
	}
}
