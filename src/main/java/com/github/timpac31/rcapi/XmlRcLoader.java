package com.github.timpac31.rcapi;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.github.timpac31.rcapi.config.ElementOption;
import com.github.timpac31.rcapi.data.RcStorageList;
import com.github.timpac31.rcapi.data.XmlRcStorage;

public class XmlRcLoader implements RcLoader {
	private ElementOption option;
	private RcStorageList storageList;
	private XmlRcStorage storage;

	public XmlRcLoader(ElementOption option) {
		this.option = option;
		this.storageList = new RcStorageList();
		storage = (XmlRcStorage) this.storageList.getRcStorage(option.getName());
		if(storage == null) 
			storage = new XmlRcStorage();
	}

	@Override
	public XmlRcStorage getStorage() throws Exception {
		if(storage.isExpired(option.getInterval())) {
			Document document = getDataFromApi();
			saveData(document);
		}
		
		return storage;
	}
	
	private Document getDataFromApi() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		return builder.parse(new URL(option.getUrl()).openStream());
	}
	
	private void saveData(Document document) {
		storage.setLastCall(System.currentTimeMillis());
		storage.setData(document);
		storageList.addRcStorage(option.getName(), storage);
	}

}
