package com.example.javaexamples.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXConsoleTreeViewer {
    private String vendorParserClass = "org.apache.xerces.parsers.SAXParser";
    
    public SAXConsoleTreeViewer() {
        System.out.println("SAX Tree Viewer");
    }

    public void init(String xmlURI) throws IOException, SAXException {
        System.out.println("XML Document: " + xmlURI);
        buildTree(xmlURI);
    }

    public void buildTree(String xmlURI) throws IOException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader(vendorParserClass);
        ContentHandler contentHandler = new ConsoleContentHandler();
        ErrorHandler errorHandler = new ConsoleErrorHandler();
        reader.setContentHandler(contentHandler);
        reader.setErrorHandler(errorHandler);
        InputSource inputSource = new InputSource(xmlURI);
        reader.parse(inputSource);

    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java myxml.SAXTreeViewer [XML Document URI]");
                System.exit(0);
            }
            SAXConsoleTreeViewer viewer = new SAXConsoleTreeViewer();
            viewer.init(args[0]);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ConsoleContentHandler implements ContentHandler {
    private Locator locator;
    private Map namespaceMappings;
    private int tabs = 0;
    
    public ConsoleContentHandler() {
        this.namespaceMappings = new HashMap();
        this.tabs = 0;
    }

    protected void tab() {
        for (int i=0 ; i<this.tabs ; i++)
            System.out.print("\t");
    }
    
    protected void print(String s) {
        tab();
        System.out.println(s);
    }
    
            
    
    public void setDocumentLocator(Locator l) {
        this.locator = l;
    }

    public void startDocument() throws SAXException { tabs++; }

    public void endDocument() throws SAXException { tabs--; }
    
    public void processingInstruction(String target, String data) throws SAXException {
        String s = new String("PI (target = '" + target + "', data = '" + data + "')");
        print(s);
    }

    public void startPrefixMapping(String prefix, String uri) {
        tabs++;
        namespaceMappings.put(uri, prefix);
    }

    public void endPrefixMapping(String prefix) {
        tabs--;
        for ( Iterator i=namespaceMappings.keySet().iterator() ; 
              i.hasNext() ; 
            ) {
            String uri = (String)i.next();
            String thisPrefix = (String)namespaceMappings.get(uri);
            if (prefix.equals(thisPrefix)) {
                namespaceMappings.remove(uri);
                break;
            }
        }
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        tabs++;
        String element = new String("Element: " + localName);
        print(element);

        if (namespaceURI.length() > 0) {
            String prefix = (String)namespaceMappings.get(namespaceURI);
            if (prefix.equals("")) {
                prefix = "[None]";
            }

            tabs++;
            String namespace = new String("Namespace: prefix = '" + prefix + "', URI = '" + namespaceURI + "'");
            print(namespace);
            tabs--;
        }

        for (int i=0 ; i<atts.getLength() ; i++) {
            tabs++;
            String attr = new String("Attribute (name = '" + atts.getLocalName(i) + "', value = '" + atts.getValue(i) + "')");
            String attURI = atts.getURI(i);
            if (attURI.length()>0) {
                String attPrefix = (String)namespaceMappings.get(namespaceURI);
                if (attPrefix.equals("")) {
                    attPrefix = "[None]";
                }
                
                tabs++;
                String attNamespace = new String("Namespace: prefix = '" + attPrefix + "', URI = '" + attURI + "'");
                print(attNamespace);
                tabs--;
            }
            print(attr);
            tabs--;
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        tabs--;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tabs++;
        String s = new String(ch, start, length);
        print("Character Data: '" + s + "'");
        tabs--;
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { }

    public void skippedEntity(String name) throws SAXException {
        tabs++;
        String skipped = new String("Skipped Entity: '" + name + "'");
        print(skipped);
        tabs--;
    }
}


class ConsoleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("**Parsing Warning**\n" + "  Line:   " + e.getLineNumber() + "\n  URI:    " + e.getSystemId() + "\n  Message:    " + e.getMessage());
        throw new SAXException("Warning encountered");
    }
    
    public void error(SAXParseException e) throws SAXException {
        System.err.println("**Parsing Warning**\n" + "  Line:   " + e.getLineNumber() + "\n  URI:    " + e.getSystemId() + "\n  Message:    " + e.getMessage());
        throw new SAXException("Error encountered");
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("**Parsing Warning**\n" + "  Line:   " + e.getLineNumber() + "\n  URI:    " + e.getSystemId() + "\n  Message:    " + e.getMessage());
        throw new SAXException("Fatal Error encountered");
    }
}
