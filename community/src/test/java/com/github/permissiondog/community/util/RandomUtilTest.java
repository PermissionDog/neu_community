package com.github.permissiondog.community.util;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomUtilTest {
	@Test
	@DisplayName("测试随机字符生成")
	void testRandomString() {
		String str = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < 10; i++) {
			String salt1 = RandomUtil.randStr(str, 16);
			String salt2 = RandomUtil.randStr(str, 16);
			assertNotEquals(salt1, salt2);
		}
	}
}
