package com.github.permissiondog.community.exception;

public class IllegalParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public IllegalParameterException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
