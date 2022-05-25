package com.github.permissiondog.community.util;

class NoSuchAlgorithmException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public NoSuchAlgorithmException(String msg) {
		this.msg = msg;
	}
	@Override
	public String getMessage() {
		return msg;
	}
}