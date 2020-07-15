package com.github.timpac31.rcapi.config;

public class RcConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 3409288664429702611L;

	public RcConfigurationException(String message) {
		super(message);
	}
	
	public RcConfigurationException(Throwable cause) {
		super(cause);
	}
	
	public RcConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
}
