package com.github.permissiondog.community.util;

import java.util.Random;

/**
 * 随机工具类
 * 
 * @author PermissionDog
 *
 */
public class RandomUtil {
	/**
	 * 用给定的字符集生成随机长度的字符串
	 * 
	 * @param str	字符集
	 * @param len	生成的字符串长度
	 * @return		生成的随机字符串
	 */
	public static String randStr(String str, int len) {
		Random rand = new Random();
		StringBuilder r = new StringBuilder();
		for (int i = 0; i < len; i++) {
			r.append(str.charAt(rand.nextInt(str.length())));
		}
		return r.toString();
	}
}
