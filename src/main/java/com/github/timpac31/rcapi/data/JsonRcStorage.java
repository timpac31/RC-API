package com.github.timpac31.rcapi.data;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonRcStorage extends RcStorage {
	private JsonNode data;

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
}
