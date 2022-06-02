package com.github.permissiondog.community.util;

/**
 * 未找到加密方法异常
 * 
 * @author PermissionDog
 *
 */
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