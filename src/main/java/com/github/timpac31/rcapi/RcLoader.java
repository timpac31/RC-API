package com.github.timpac31.rcapi;

import com.github.timpac31.rcapi.data.RcStorage;

public interface RcLoader {
	/**
	 * Loader의 interval(ms) 옵션을 비교해서
	 * (마지막 API요청시간 + interval시간)이 현재시간을 지나지 않았으면 저장된 Storage를 리턴, 지났으면 API 재요청하여 저장한 Storage를 리턴한다.
	 */
	public RcStorage getStorage() throws Exception;
}