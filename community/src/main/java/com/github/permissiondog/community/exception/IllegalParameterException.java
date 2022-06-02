package com.github.permissiondog.community.exception;

/**
 * 非法参数异常
 * 
 * @author PermissionDog
 *
 */
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
