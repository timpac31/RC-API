package com.github.timpac31.rcapi.data;

import org.w3c.dom.Document;

public class XmlRcStorage extends RcStorage {
	private Document data;

	public Document getData() {
		return data;
	}

	public void setData(Document data) {
		this.data = data;
	}
}
