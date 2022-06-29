package com.example.javaexamples.xml;

import org.apache.commons.jxpath.JXPathContext;

//import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;

public class TryJXPath2 {
    public static void main(String[] args) {
        CursorObj cursorObj = new CursorObj();
//        cursorObj.setCredencial(new JAXBElement<String>(QName.valueOf("http://string"), String.class, "11"));
//        cursorObj.setEmporigem(new JAXBElement<String>(QName.valueOf("http://string"), String.class, "22"));
//        cursorObj.setOrigemcontrato(new JAXBElement<String>(QName.valueOf("http://string"), String.class, "33"));

        DBObj dbObj = new DBObj();
        dbObj.setpCursor(Arrays.asList(cursorObj));
        dbObj.setpCredencial("hello_world");

        JXPathContext context = JXPathContext.newContext(dbObj);

        Object obj = context.getValue(".");
        System.out.println(obj.toString());

        obj = context.getValue("/pCredencial");
        System.out.println(obj.toString());

        // This fails
        //obj = context.getValue("/p_credencial");
        //System.out.println(obj.toString());

        obj = context.getValue("/pCredencial = '44'");
        System.out.println(obj.toString());

        obj = context.getValue("/pCursor/credencial/value = '11'");
        System.out.println(obj.toString());

        obj = context.getValue("string-length(/pCredencial)");
        System.out.println(obj.toString());

    }
}
