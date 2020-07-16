package com.github.timpac31.rcapi;

import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.timpac31.rcapi.config.ElementOption;
import com.github.timpac31.rcapi.data.JsonRcStorage;
import com.github.timpac31.rcapi.data.RcStorageList;
import com.github.timpac31.rcapi.util.UrlReader;

public class JsonRcLoader implements RcLoader {
	private ElementOption option;
	private JsonRcStorage storage;
	
	protected JsonRcLoader(ElementOption option) {
		this.option = option;
		storage = (JsonRcStorage) RcStorageList.getRcStorage(option.getName());
		if(storage == null) 
			storage = new JsonRcStorage();
	}

	@Override
	public JsonRcStorage getStorage() throws MalformedURLException, IOException {
		if(storage.isExpired(option.getInterval())) {
			JsonNode node = getDataFromApi();
			saveData(node);
		}
		
		return storage;
	}

	private JsonNode getDataFromApi() throws MalformedURLException, IOException {
		String responseData = UrlReader.readFromUrl(option.getUrl());
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(responseData);
	}
	
	private void saveData(JsonNode node) {
		storage.setLastCall(System.currentTimeMillis());
		storage.setData(node);
		RcStorageList.addRcStorage(option.getName(), storage);
	}

}
