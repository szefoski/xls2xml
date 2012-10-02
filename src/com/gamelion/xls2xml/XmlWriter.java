package com.gamelion.xls2xml;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {

	public void write( String destinationDirecory ) {
		for (  Map.Entry<String, Dict> val: Bank.dicts.entrySet() ) {
			File destFilePath = new File(destinationDirecory, val.getKey() + ".xml");
			writeLang(destFilePath, val.getValue());
		}
	}
	
	private void writeLang( File dest, Dict dict ) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("strings");
			doc.appendChild(rootElement);
			
			for ( DictElement e : dict.elements) {
				Element string = doc.createElement("string");
				string.setAttribute("id", e.key);
				string.appendChild(doc.createTextNode(e.value));
				rootElement.appendChild(string);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(dest);

			transformer.transform(source, result);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 

	}
}
