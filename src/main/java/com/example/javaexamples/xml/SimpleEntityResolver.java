package com.example.javaexamples.xml;

import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SimpleEntityResolver implements EntityResolver {
    public InputSource resolveEntity(String publicID, String systemID) throws IOException, SAXException {
        System.out.println("Found entity with public ID " + publicID + " and system ID " + systemID);
        return null;
    }
}

    
