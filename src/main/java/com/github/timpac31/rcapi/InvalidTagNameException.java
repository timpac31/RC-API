package com.github.timpac31.rcapi;

public class InvalidTagNameException extends RuntimeException {
	private static final long serialVersionUID = 5813545962399252549L;

	public InvalidTagNameException(String message) {
		super(message);
	}
	
	public InvalidTagNameException(Throwable cause) {
		super(cause);
	}
	
	public InvalidTagNameException(String message, Throwable cause) {
		super(message, cause);
	}
}
