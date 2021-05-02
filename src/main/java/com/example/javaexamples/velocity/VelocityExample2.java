package com.example.javaexamples.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Properties;

public class VelocityExample2 {
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init( p );

        Template t = velocityEngine.getTemplate("loop.vm");

        HashMap data = new HashMap();
        data.put("test1", "value1");
        data.put("test2", "value2");
        data.put("test3", "value3");
        data.put("test4", "value4");
        data.put("test5", "value5");

        VelocityContext context = new VelocityContext();
        context.put("allProducts", data);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        System.out.println(writer.toString());
    }
}
