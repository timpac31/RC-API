package com.github.timpac31.rcapi.data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 여러개의 RcStorage를 저장하는 클래스
 * ConcurrentMap<String, RcStorage>에 ElementOption의 name이 key로, RcStorage가 value로 저장된다.
 */
public class RcStorageList {
	private static final ConcurrentMap<String, RcStorage> storages = new ConcurrentHashMap<>();
	
	public static boolean contains(String storageName) {
		return storages.containsKey(storageName);
	}
	
	public static RcStorage getRcStorage(String storageName) {
		return storages.get(storageName);
	}
	
	public static void addRcStorage(String key, RcStorage rcStorage) {
		storages.put(key, rcStorage);
	}
}
