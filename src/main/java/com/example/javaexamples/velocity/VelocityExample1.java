package com.example.javaexamples.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

public class VelocityExample1 {
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init( p );

        Template t = velocityEngine.getTemplate("index.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "World");

        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        System.out.println(writer.toString());
    }
}
