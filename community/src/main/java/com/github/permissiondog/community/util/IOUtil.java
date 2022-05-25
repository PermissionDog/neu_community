package com.github.permissiondog.community.util;

import java.io.*;

public class IOUtil {
	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[2048];
		int pos;
		while ((pos = in.read(buf)) != -1) {
			out.write(buf, 0, pos);
		}
	}
}
