package com.github.timpac31.rcapi.config;

public enum DataType {
	JSON("json"), XML("xml");
	
	private String name;
	DataType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
