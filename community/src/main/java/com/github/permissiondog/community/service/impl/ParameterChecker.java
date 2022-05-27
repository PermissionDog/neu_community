package com.github.permissiondog.community.service.impl;

import com.github.permissiondog.community.exception.IllegalParameterException;

public class ParameterChecker {
	protected static boolean checkUserName(String username) {
		if (username == null) {
			return false;
		}
		return username.matches("^\\w{4,18}$");
	}
	
	protected static boolean checkPassword(String password) {
		if (password == null) {
			return false;
		}
		int count = 0;
		if (password.matches(".*[a-z].*")) {
			count++;
		}
		if (password.matches(".*[A-Z].*")) {
			count++;
		}
		if (password.matches(".*[0-9].*")) {
			count++;
		}
		if (password.matches(".*[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\.\\_].*")) {
			count++;
		}
		if (count < 2) {
			return false;
		}
		return password.matches("^[\\w\\~\\!\\@\\#\\$\\%\\^\\&\\*\\.]{8,18}$");
	}
	
	protected static boolean checkName(String name) {
		if (name == null) {
			return false;
		}
		return name.matches("^[\\u4e00-\\u9fa5a-zA-Z0-9]{1,10}$");
	}
	
	protected static boolean checkPhone(String phone) {
		if (phone == null) {
			return false;
		}
		return phone.matches("^[0-9\\+]{5,20}$");
	}
	

	protected static void ensureNotEmpty(Object obj, String paraName) throws IllegalParameterException {
		if (obj == null || (obj instanceof String && "".equals((String) obj))) {
			throw new IllegalParameterException(paraName + " 不能为空");
		}
	}

	protected static void ensure(boolean legal, String msg) throws IllegalParameterException {
		if (!legal)
			throw new IllegalParameterException(msg);
	}
}
