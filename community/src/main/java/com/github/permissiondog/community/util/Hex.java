package com.github.permissiondog.community.util;

/**
 * 十六进制工具类
 * 
 * @author PermissionDog
 *
 */
public class Hex {
	private static final String BASE = "0123456789abcdef";
	/**
	 * 将 byte 数组转化为十六进制字符串
	 * 
	 * @param data	要转化的数据
	 * @return		生成的十六进制字符串
	 */
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
