package com.example.javaexamples.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class VelocityExample3 {
    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init( p );

        Template t = velocityEngine.getTemplate("listofmaps.vm");

        List lst = new ArrayList<>();

        for (int i=0 ; i<10 ; i++) {
            Map data = new LinkedHashMap();
            data.put("test1", "value1"+ "_" + i);
            data.put("test2", "value2"+ "_" + i);
            data.put("test3", "value3"+ "_" + i);

            lst.add(data);
        }

        VelocityContext context = new VelocityContext();
        context.put("lst", lst);

        StringWriter writer = new StringWriter();
        t.merge( context, writer );

        System.out.println(writer.toString());
    }
}
