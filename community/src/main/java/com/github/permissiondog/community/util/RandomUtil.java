package com.github.permissiondog.community.util;

import java.util.Random;

public class RandomUtil {
	public static String randStr(String str, int len) {
		Random rand = new Random();
		StringBuilder r = new StringBuilder();
		for (int i = 0; i < len; i++) {
			r.append(str.charAt(rand.nextInt(str.length())));
		}
		return r.toString();
	}
}
