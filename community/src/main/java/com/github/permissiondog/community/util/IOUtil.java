package com.github.permissiondog.community.util;

import java.io.*;

/**
 * IO 相关
 * 
 * @author PermissionDog
 *
 */
public class IOUtil {
	/**
	 * 将输入流的数据输出到输出流中
	 * 
	 * @param in			输入流
	 * @param out			输出流
	 * @throws IOException	IO异常
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[2048];
		int pos;
		while ((pos = in.read(buf)) != -1) {
			out.write(buf, 0, pos);
		}
	}
}
