package com.example.javaexamples.xml;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.SAXException;

public class NamespaceFilter extends XMLFilterImpl {
    private String oldURI;
    private String newURI;
    
    public NamespaceFilter(XMLReader reader, String oldURI, String newURI) {
        super(reader);
        this.oldURI = oldURI;
        this.newURI = newURI;
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (uri.equals(oldURI)) {
            super.startPrefixMapping(prefix, newURI);
        } else {
            super.startPrefixMapping(prefix, uri);
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (uri.equals(oldURI)) {
            super.startElement(newURI, localName, qName, attributes);
        } else {
            super.startElement(uri, localName, qName, attributes);
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (uri.equals(oldURI)) {
            super.endElement(newURI, localName, qName);
        } else {
            super.endElement(uri, localName, qName);
        }
    }
}

    
