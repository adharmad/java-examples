package com.example.javaexamples.xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class TryXSLT1 {

    public static final String xmlStr = "<PHONEBOOK>\n" +
            "<PERSON>\n" +
            " <NAME>Joe Wang</NAME>\n" +
            " <EMAIL>joe@yourserver.com</EMAIL>\n" +
            " <TELEPHONE>202-999-9999</TELEPHONE>\n" +
            " <WEB>www.java2s.com</WEB>\n" +
            "</PERSON>\n" +
            "<PERSON>\n" +
            " <NAME>Karol</NAME>\n" +
            " <EMAIL>karol@yourserver.com</EMAIL>\n" +
            " <TELEPHONE>306-999-9999</TELEPHONE>\n" +
            " <WEB>www.java2s.com</WEB>\n" +
            "</PERSON>\n" +
            "<PERSON>\n" +
            " <NAME>Green</NAME>\n" +
            " <EMAIL>green@yourserver.com</EMAIL>\n" +
            " <TELEPHONE>202-414-9999</TELEPHONE>\n" +
            " <WEB>www.java2s.com</WEB>\n" +
            "</PERSON>\n" +
            "</PHONEBOOK>";

    public static final String xsltStr = "<?xml version=\"1.0\"?>\n" +
            "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
            "<xsl:template match=\"/\">\n" +
            "\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Directory</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<table border=\"1\">\n" +
            "\n" +
            "<tr>\n" +
            " <th>Name</th>\n" +
            " <th>Telephone</th>\n" +
            " <th>Email</th>\n" +
            "</tr>\n" +
            "\n" +
            "<xsl:for-each select=\"PHONEBOOK/PERSON\">\n" +
            " <xsl:sort/>\n" +
            " <tr>\n" +
            "  <td><xsl:value-of select=\"NAME\"/></td>\n" +
            "  <td><xsl:value-of select=\"TELEPHONE\"/></td>\n" +
            "  <td><xsl:value-of select=\"EMAIL\"/></td>\n" +
            " </tr>\n" +
            "</xsl:for-each>\n" +
            "\n" +
            "</table>\n" +
            "</body>\n" +
            "</html>\n" +
            "</xsl:template>\n" +
            "</xsl:stylesheet>";

    public static void main(String args[]) throws Exception {
        StringReader xmlsr = new StringReader(xmlStr);
        StringReader xsltsr = new StringReader(xsltStr);
        StreamSource source = new StreamSource(xmlsr);
        StreamSource stylesource = new StreamSource(xsltsr);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);

        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}