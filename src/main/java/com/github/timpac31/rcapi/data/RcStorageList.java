package com.github.timpac31.rcapi.data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RcStorageList {
	private static final ConcurrentMap<String, RcStorage> storages = new ConcurrentHashMap<>();
	
	public boolean contains(String storageName) {
		return storages.containsKey(storageName);
	}
	
	public RcStorage getRcStorage(String storageName) {
		return storages.get(storageName);
	}
	
	public void addRcStorage(String key, RcStorage rcStorage) {
		storages.put(key, rcStorage);
	}
}
