package com.example.javaexamples.reflection;

import org.apache.commons.lang.reflect.MethodUtils;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class InvokeStaticMethod {
    public static void main(String[] args) throws Exception {
        Object[] params = {
            "hello", "poor", "world"
        };
        Object ret = MethodUtils.invokeExactStaticMethod(ClassHavingStaticMethod.class, "staticMethod", params);

        System.out.println(ret);
    }
}
