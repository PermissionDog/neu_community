package com.github.permissiondog.community.service.impl;

public class ParameterChecker {
	public static boolean checkUserName(String username) {
		if (username == null) {
			return false;
		}
		return username.matches("^\\w{4,18}$");
	}
	
	public static boolean checkPassword(String password) {
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
	
	public static boolean checkName(String name) {
		if (name == null) {
			return false;
		}
		return name.matches("^[\\u4e00-\\u9fa5a-zA-Z0-9]{1,10}$");
	}
	
	public static boolean checkPhone(String phone) {
		if (phone == null) {
			return false;
		}
		return phone.matches("^[0-9\\+]{5,20}$");
	}
}
