package com.github.permissiondog.community.util;

import java.security.MessageDigest;

/**
 * 加密工具类
 * 
 * 主要进行密码加密, 密码验证
 * 
 * @author PermissionDog
 *
 */
public class Encrypt {
	
	
	/**
	 * 用于生成盐的字符
	 */
	private static final String SALT_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";
	
	/**
	 * 使用指定算法加密密码
	 * 盐为随机生成的16位字符串
	 * 
	 * @param algorithm 支持的算法 "SHA-512", "SHA-256", "MD5", "PLAINTEXT"
	 * @param password	密码
	 * @return 十六进制密文字符串
	 */
	public static String encryptPassword(String algorithm, String password) {
		if ("PLAINTEXT".equals(algorithm)) {
			return encryptPassword(algorithm, password, "");
		}
		return encryptPassword(algorithm, password, RandomUtil.randStr(SALT_CHARS, 16));
	}
	/**
	 * 使用指定算法加密密码
	 * 
	 * @param algorithm 支持的算法 "SHA-512", "SHA-256", "MD5", "PLAINTEXT"
	 * @param password	密码
	 * @param salt		盐
	 * @return 十六进制密文字符串
	 */
	public static String encryptPassword(String algorithm, String password, String salt) {
		algorithm = algorithm.toUpperCase();
		switch (algorithm) {
		case "SHA-512":
		case "SHA-256":
		case "MD5":
		case "PLAINTEXT":
			break;
		default:
			throw new NoSuchAlgorithmException("不支持的算法 " + algorithm);
		}
		if ("PLAINTEXT".equals(algorithm)) {
			return algorithm + ":" + salt + ":" + password;
		}
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException("不支持的算法 " + algorithm);
		}
		byte[] data = md.digest((password + salt).getBytes());
		
		
		return algorithm + ":" + salt + ":" + Hex.toHexString(data);
	}

	/**
	 * 检查密码是否正确
	 * 
	 * @param password		要检测的密码
	 * @param cypheredPwd	加密过的密码
	 * @return				正确返回 true
	 */
	public static boolean check(String password, String cypheredPwd) {
		String[] temp = cypheredPwd.split(":");
		if (temp.length < 3) {
			return false;
		}
		
		return encryptPassword(temp[0], password, temp[1]).equals(cypheredPwd);
	}
}
