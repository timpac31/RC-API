package com.github.timpac31.rcapi;

import com.github.timpac31.rcapi.data.RcStorage;

public interface RcLoader {
	public RcStorage getStorage() throws Exception;
}