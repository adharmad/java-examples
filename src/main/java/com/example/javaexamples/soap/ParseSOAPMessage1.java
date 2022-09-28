package com.example.javaexamples.soap;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

public class ParseSOAPMessage1 {
    public static void main(String[] args) throws Exception {
        String soapMsgStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.wserv.ecurp.dgti.segob.gob.mx\">\n" +
                "  <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "\t\t<ns:consultarPorCurpResponse>\n" +
                "\t\t<CURPStruct statusOper=\"EXITOSO\" message=\"LA OPERACION SE EJECUTO.\" TipoError=\"\" CodigoError=\"\" SessionID=\"P158eu_T0_SwNXH3Vwojpeu30Sxqlki33K7lCzhK0LqnYmGApc5V!311886685!1664240775123\"><CURP>POML881019HDFNRV01</CURP><apellido1>PONCE</apellido1><apellido2>MARTINEZ</apellido2><nombres>LEVY JASEF</nombres><sexo>H</sexo><fechNac>19/10/1988</fechNac><nacionalidad>MEX</nacionalidad><docProbatorio>1</docProbatorio><anioReg>1988</anioReg><foja></foja><tomo></tomo><libro></libro><numActa>10405</numActa><CRIP></CRIP><numEntidadReg>09</numEntidadReg><cveMunicipioReg>005</cveMunicipioReg><NumRegExtranjeros></NumRegExtranjeros><FolioCarta></FolioCarta><cveEntidadNac>DF</cveEntidadNac><cveEntidadEmisora></cveEntidadEmisora><statusCurp>RCN</statusCurp></CURPStruct>\n" +
                "\t\t</ns:consultarPorCurpResponse>  \n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        SOAPMessage soapMessage = getSoapMessage(soapMsgStr);
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        StringBuilder resultBuilder = new StringBuilder();

        // Get all children of the given parent node
        NodeList children = document.getChildNodes();

        // Set up the output transformer
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);

        Node child = children.item(0);
        Node grandChild = child.getFirstChild().getNextSibling();

        // Print the DOM node
        DOMSource source = new DOMSource(grandChild);
        transformer.transform(source, streamResult);

        // Append child to end result
        resultBuilder.append(stringWriter.toString());

        System.out.println(resultBuilder.toString());
    }

    public static SOAPMessage getSoapMessage(String xml) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return factory.createMessage(new MimeHeaders(), byteArrayInputStream);
    }

    public static Node getFirstElement(SOAPMessage message) throws SOAPException {
        final NodeList childNodes = message.getSOAPBody().getChildNodes();
        Node firstElement = null;
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                firstElement = childNodes.item(i);
                break;
            }
        }
        return firstElement;
    }
}
