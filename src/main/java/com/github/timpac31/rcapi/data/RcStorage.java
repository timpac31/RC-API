package com.github.timpac31.rcapi.data;

public abstract class RcStorage {
	private String name;
	private long lastCall = 0L;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getLastCall() {
		return lastCall;
	}
	
	public void setLastCall(long lastCall) {
		this.lastCall = lastCall;
	}
	
	/**
	 * 마지막 API요청 후 interval(ms) 지났으면 현재시간으로 마지막 요청시간 업데이트 후 true 리턴, 아니면  false 리턴
	 * @param interval millisecond
	 */
	public boolean isExpired(long interval) {
		long curTime = System.currentTimeMillis();
		if((lastCall + interval) < curTime) {
			lastCall = curTime;
			return true;
		}
		return false;
	}
}
