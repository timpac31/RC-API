package com.github.timpac31.rcapi.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JacksonTest {
	@Test
	public void testJackson() throws MalformedURLException, IOException {
		InputStream json = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/github/timpac31/rcapi/json/example.json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(json);
		
		JsonNode itemNode = node.at("/response/body/items/item");
		ArrayNode items = null;
		if(itemNode.isArray()) 
			items = (ArrayNode)itemNode;
		
		for(int i=0; i<items.size(); i++) {
			assertEquals("LGT", items.get(i).get("category").asText());
			assertEquals(0, items.get(i).get("fcstValue").asInt());
			assertEquals(58, items.get(i).get("nx").asInt());
			assertEquals(126, items.get(i).get("ny").asInt());
		}
		
	}
	
}
