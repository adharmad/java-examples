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
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

public class SAXTreeViewer extends JFrame {
    private String vendorParserClass = "org.apache.xerces.parsers.SAXParser";
    private JTree jTree;
    private DefaultTreeModel defaultTreeModel;
    
    public SAXTreeViewer() {
        super("SAX Tree Viewer");
        setSize(600, 450);
    }

    public void init(String xmlURI) throws IOException, SAXException {
        DefaultMutableTreeNode base = new DefaultMutableTreeNode("XML Document: " + xmlURI);
        defaultTreeModel = new DefaultTreeModel(base);
        jTree = new JTree(defaultTreeModel);
        buildTree(defaultTreeModel, base, xmlURI);
        getContentPane().add(new JScrollPane(jTree), BorderLayout.CENTER);
    }

    public void buildTree(DefaultTreeModel treeModel, DefaultMutableTreeNode base, String xmlURI) throws IOException, SAXException {
        //String featureURI = "";
        //try {
        XMLReader reader = XMLReaderFactory.createXMLReader(vendorParserClass);
        NamespaceFilter filter = new NamespaceFilter(reader, "http://www.oreilly.com/javaxml2", "http://www.oreilly.com/catalog/javaxml2");
        ContentHandler jTreeContentHandler = new JTreeContentHandler(
            treeModel, base);
        ErrorHandler jTreeErrorHandler = new JTreeErrorHandler();
        filter.setContentHandler(jTreeContentHandler);
        filter.setErrorHandler(jTreeErrorHandler);
        filter.setEntityResolver(new SimpleEntityResolver());
        //reader.setContentHandler(jTreeContentHandler);
        //reader.setErrorHandler(jTreeErrorHandler);
        //reader.setEntityResolver(new SimpleEntityResolver());
        /*
          featureURI = "http://xml.org/sax/features/validation";
          reader.setFeature(featureURI, true);
          featureURI = "http://xml.org/sax/features/namespaces";
          setNamespaceProcessing(reader, true);
          featureURI = "http://xml.org/sax/features/string-interning";
          reader.setFeature(featureURI, true);
          featureURI = "http://apache.org/xml/features/validation/schema";
          reader.setFeature(featureURI, false);
        */
        InputSource inputSource = new InputSource(xmlURI);
        filter.parse(inputSource);
        //reader.parse(inputSource);
        /*
          } catch (SAXNotRecognizedException e) {
          System.err.println("The Parser class " + vendorParserClass + " does not recognize the feature URI " + featureURI);
          System.exit(0);
          } catch (SAXNotSupportedException e) {
          System.err.println("The Parser class " + vendorParserClass + " does not support the feature URI " + featureURI);
          System.exit(0);
          }
        */
    }
    
    public void setNamespaceProcessing(XMLReader reader, boolean state) throws SAXNotSupportedException, SAXNotRecognizedException {
        reader.setFeature("http://xml.org/features/namespaces", state);
        reader.setFeature("http://xml.org/features/namespace-prefixes", !state);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java myxml.SAXTreeViewer [XML Document URI]");
                System.exit(0);
            }
            SAXTreeViewer viewer = new SAXTreeViewer();
            viewer.init(args[0]);
            viewer.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class JTreeContentHandler implements ContentHandler {
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode current;
    private Locator locator;
    private Map namespaceMappings;
    
    public JTreeContentHandler(DefaultTreeModel treeModel, DefaultMutableTreeNode base) {
        this.treeModel = treeModel;
        this.current = base;
        this.namespaceMappings = new HashMap();
    }
    
    public void setDocumentLocator(Locator l) {
        this.locator = l;
    }

    public void startDocument() throws SAXException { }

    public void endDocument() throws SAXException { }
    
    public void processingInstruction(String target, String data) throws SAXException {
        DefaultMutableTreeNode pi = new DefaultMutableTreeNode("PI (target = '" + target + "', data = '" + data + "')");
        current.add(pi);
    }

    public void startPrefixMapping(String prefix, String uri) {
        namespaceMappings.put(uri, prefix);
    }

    public void endPrefixMapping(String prefix) {
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
        DefaultMutableTreeNode element = new DefaultMutableTreeNode("Element: " + localName);
        current.add(element);
        current = element;

        if (namespaceURI.length() > 0) {
            String prefix = (String)namespaceMappings.get(namespaceURI);
            if (prefix.equals("")) {
                prefix = "[None]";
            }

            DefaultMutableTreeNode namespace = new DefaultMutableTreeNode("Namespace: prefix = '" + prefix + "', URI = '" + namespaceURI + "'");
            current.add(namespace);
        }

        for (int i=0 ; i<atts.getLength() ; i++) {
            DefaultMutableTreeNode attr = new DefaultMutableTreeNode("Attribute (name = '" + atts.getLocalName(i) + "', value = '" + atts.getValue(i) + "')");
            String attURI = atts.getURI(i);
            if (attURI.length()>0) {
                String attPrefix = (String)namespaceMappings.get(namespaceURI);
                if (attPrefix.equals("")) {
                    attPrefix = "[None]";
                }
                
                DefaultMutableTreeNode attNamespace = new DefaultMutableTreeNode("Namespace: prefix = '" + attPrefix + "', URI = '" + attURI + "'");
                
                attr.add(attNamespace);
            }
            current.add(attr);
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        current = (DefaultMutableTreeNode)current.getParent();
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        DefaultMutableTreeNode data = new DefaultMutableTreeNode("Character Data: '" + s + "'");
        current.add(data);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException { }

    public void skippedEntity(String name) throws SAXException {
        DefaultMutableTreeNode skipped = new DefaultMutableTreeNode("Skipped Entity: '" + name + "'");
        current.add(skipped);
    }
}


class JTreeErrorHandler implements ErrorHandler {
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
