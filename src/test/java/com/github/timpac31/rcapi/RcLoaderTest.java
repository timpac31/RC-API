package com.github.timpac31.rcapi;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.timpac31.rcapi.data.JsonRcStorage;
import com.github.timpac31.rcapi.data.XmlRcStorage;

public class RcLoaderTest {
	//@Test
	public void testJsonRcLoader() throws Exception {
		RcLoader loader = RcLoaderFactory.createRcLoader("instagram");
		JsonRcStorage storage = (JsonRcStorage) loader.getStorage();
		JsonNode root = storage.getData();
		System.out.println("root:" + root);
		JsonNode node = root.get("data");
		ArrayNode items = null;
		if(node.isArray()) 
			items = (ArrayNode)node;
		
		for(int i=0; i<items.size(); i++) {
			System.out.println(items.get(i).get("id").asText());
			System.out.println(items.get(i).get("caption").asText());
			System.out.println(items.get(i).get("permalink").asText());
		}
	}
	
	//@Test
	public void testXmlRcLoader() throws Exception {
		RcLoader loader = RcLoaderFactory.createRcLoader("wheather-api-seoul");
		XmlRcStorage storage = (XmlRcStorage) loader.getStorage();
		
		Document document = storage.getData();
		NodeList nodeList = document.getElementsByTagName("item");
		
		for(int i=0; i<nodeList.getLength(); i++) {
			System.out.println( nodeToString(nodeList.item(i), "title") );
			System.out.println( nodeToString(nodeList.item(i), "pubDate") );
			System.out.println( nodeToString(nodeList.item(i), "link") );
		}
		
	}
	
	private String nodeToString(Node node, String tagName) {
		return Element.class.cast(node).getElementsByTagName(tagName).item(0).getTextContent();
	}
	
}
