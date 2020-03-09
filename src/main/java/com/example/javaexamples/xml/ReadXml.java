package com.example.javaexamples.xml;

import java.io.CharArrayWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXml {
	public static void main(String[] args) throws Exception {
		// Instantiate a DocumentBuilderFactory.
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		// Use the DocumentBuilderFactory to provide access to a
		// DocumentBuilder.
		DocumentBuilder dBuilder = dfactory.newDocumentBuilder();
		// Use the DocumentBuilder to parse the XML input.
		//Document inDoc = dBuilder.parse("c:/tmp/foo.xml");
		
		StringReader sr = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?><xl-ddm-data database=\"jdbc:oracle:thin:@tde10:1522:CUSTOMER\" description=\"errtest\" exported-date=\"1188936879906\" user=\"XELSYSADM\" version=\"1.1.0.4\"><EventHandler name=\"adpERRTEST\" subtype=\"TaskAdapter\"><EVT_POST_DELETE>0</EVT_POST_DELETE><EVT_PACKAGE>com.thortech.xl.adapterGlue.ScheduleItemEvents</EVT_PACKAGE><EVT_PRE_DELETE>0</EVT_PRE_DELETE><EVT_PRE_UPDATE>0</EVT_PRE_UPDATE><EVT_PRE_INSERT>0</EVT_PRE_INSERT><EVT_POST_UPDATE>1</EVT_POST_UPDATE><EVT_POST_INSERT>1</EVT_POST_INSERT><EVT_UPDATE>1188936655000</EVT_UPDATE><Adapter name=\"errtest\"><ADP_BUILD>0</ADP_BUILD><ADP_DESCRIPTION>errtest</ADP_DESCRIPTION><ADP_TYPE>T</ADP_TYPE><ADP_STATUS>OK</ADP_STATUS><ADP_DISABLED>0</ADP_DISABLED><ADP_UPDATE>1188936775000</ADP_UPDATE><AdapterVariable name=\"Adapter return value\"><ADV_DESC>Return variable</ADV_DESC><ADV_UPDATE>1188936775000</ADV_UPDATE><ADV_MAP_TO>UNUSED</ADV_MAP_TO><ADV_DATA_TYPE>Object</ADV_DATA_TYPE><ADV_DISPLAY_VALUE>Return variable</ADV_DISPLAY_VALUE></AdapterVariable><AdapterTask name=\"errtest\"><ADT_UPDATE>1188936768000</ADT_UPDATE><ADT_TYPE>JAVA</ADT_TYPE><ADT_SEQUENCE>0</ADT_SEQUENCE><AdapterJavaTask id=\"ADJ481\"><ADJ_METHOD>test1</ADJ_METHOD><ADJ_JAR_FILE>testclass.jar</ADJ_JAR_FILE><ADJ_API_NAME>com.thortech.xl.drivers.adapters.TestClass1</ADJ_API_NAME><ADJ_CONSTRUCTOR>0  public com.thortech.xl.drivers.adapters.TestClass1()</ADJ_CONSTRUCTOR><ADJ_STATIC>0</ADJ_STATIC><ADJ_UPDATE>1188936769000</ADJ_UPDATE><ADJ_METHOD_DISPLAY>0  public void com.thortech.xl.drivers.adapters.TestClass1.test1()</ADJ_METHOD_DISPLAY><RLO_KEY ExternalDirectory=\"JavaTaskJar\"/></AdapterJavaTask><AdapterTaskParam name=\"::~~::1:Output:method\"><ATP_IMAGE>parameter.gif</ATP_IMAGE><ATP_UPDATE>1188936769000</ATP_UPDATE><ATP_JAVA_METHOD_TYPE>method</ATP_JAVA_METHOD_TYPE><ATP_TYPE>void</ATP_TYPE><ATP_IN_OUT_FLAG>Output</ATP_IN_OUT_FLAG><ATP_SEQUENCE>1</ATP_SEQUENCE></AdapterTaskParam></AdapterTask></Adapter></EventHandler></xl-ddm-data>");
		InputSource is = new InputSource(sr);
		Document inDoc = dBuilder.parse(is);
		System.out.println(getStringValue(inDoc));
	}

	public static String getStringValue(Document document)
			throws TransformerException {
		CharArrayWriter writer = new CharArrayWriter();
		StreamResult result = new StreamResult(writer);
		Transformer transformer = null;
		TransformerFactory factory = TransformerFactory.newInstance();
		transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		System.out.println("In getStringValue():: document = " + document);
		System.out.println("In getStringValue():: documentelement = "
				+ document.getDocumentElement());

		try {
			transformer.transform(new DOMSource(document.getDocumentElement()),
					result);
		} catch (TransformerException e) {
			System.out
					.println("XMLProcessor->getStringValue(): TransformerException occured while transforming");
			System.out.println(e.getMessageAndLocation());
		} catch (NullPointerException e) {
			System.out
					.println("XMLProcessor->getStringValue(): NullPointerException occured while transforming");
			System.out
					.println("XMLProcessor->getStringValue(): Dumping DOM Tree");
			System.out.println("------------------------------------------");
			Element elem = document.getDocumentElement();
			NodeList lst = elem.getChildNodes();
			dumpNodeList(lst, 1);
			System.out.println("------------------------------------------");
		}
		return new String(writer.toCharArray());
	}

	private static void dumpNodeList(NodeList lst, int tab) {
		for (int i = 0; i < lst.getLength(); i++) {
			Node n = lst.item(i);
			System.out.println(tabs[i] + n.getNodeName() + " : "
					+ n.getNodeValue());
			NodeList nlst = n.getChildNodes();
			dumpNodeList(nlst, tab + 1);
		}
	}

	private static final String[] tabs = { "", "\t", "\t\t", "\t\t\t",
			"\t\t\t\t", "\t\t\t\t\t", "\t\t\t\t\t\t", "\t\t\t\t\t\t\t",
			"\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t\t",
			"\t\t\t\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t\t\t\t",
			"\t\t\t\t\t\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
			"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"};

}
