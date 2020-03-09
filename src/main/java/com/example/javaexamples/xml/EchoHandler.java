package com.example.javaexamples.xml;

import java.io.PrintStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EchoHandler extends DefaultHandler {
	static private PrintStream out;

	public EchoHandler() {
		out = System.out;
	}

	private void emit(String s) throws SAXException {
		out.print(s);
		out.flush();
	}

	private void nl() throws SAXException {
		String lineEnd = System.getProperty("line.separator");
		out.print(lineEnd);

	}

	public void startDocument() throws SAXException {
		emit("<?xml version='1.0' encoding='UTF-8'?>");
		nl();
	}

	public void endDocument() throws SAXException {
		nl();
		out.flush();
	}

	public void startElement(String namespaceURI, String sName, // simple name (localName)
			String qName, // qualified name
			Attributes attrs) throws SAXException {
		String eName = sName; // element name
		if ("".equals(eName))
			eName = qName; // namespaceAware = false
		emit("<" + eName);
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i); // Attr name
				if ("".equals(aName))
					aName = attrs.getQName(i);
				emit(" ");
				emit(aName + "=\"" + attrs.getValue(i) + "\"");
			}
		}
		emit(">");
	}

	public void endElement(String namespaceURI, String sName, // simple name
			String qName // qualified name
	) throws SAXException {
		emit("</" + sName + ">");
	}

	public void characters(char buf[], int offset, int len) throws SAXException {
		String s = new String(buf, offset, len);
		emit(s);
	}

}
