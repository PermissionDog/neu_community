package com.github.permissiondog.community.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 十六进制转换测试
 * 
 * @author PermissionDog
 *
 */
public class HexTest {

	@Test
	@DisplayName("测试十六进制")
	void testHex() {
		assertEquals(Hex.toHexString("abc".getBytes()), "616263");;
		assertEquals(Hex.toHexString(null), "");
		assertEquals(Hex.toHexString("啊".getBytes(Charset.forName("utf-8"))), "e5958a");
	}
	
}
