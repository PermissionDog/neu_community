package com.github.permissiondog.community.util;

public class Hex {
	private static final String BASE = "0123456789abcdef";
	public static String toHexString(byte[] data) {
		if (data == null) {
			return "";
		}
		StringBuilder r = new StringBuilder();
		for (byte v : data) {
			int t = ((int) v) & 0xff;
			r.append(BASE.charAt((t & 0xf0) >> 4));
			r.append(BASE.charAt(t & 0x0f));
		}
		return r.toString();
	}
}
