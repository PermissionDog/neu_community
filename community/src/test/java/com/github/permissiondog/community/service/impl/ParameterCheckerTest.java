package com.github.permissiondog.community.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 参数校验测试
 * 
 * @author PermissionDog
 *
 */
public class ParameterCheckerTest {

	@Test
	@DisplayName("用户名")
	void testUserName() {
		assertTrue(ParameterChecker.checkUserName("root"));
		assertTrue(ParameterChecker.checkUserName("123456789012345678"));
		assertTrue(ParameterChecker.checkUserName("root_233"));
		
		assertFalse(ParameterChecker.checkUserName("root啊"));
		assertFalse(ParameterChecker.checkUserName("roo"));
		assertFalse(ParameterChecker.checkUserName(""));
		assertFalse(ParameterChecker.checkUserName("1234567890123456789"));
		assertFalse(ParameterChecker.checkUserName(null));
	}
	@Test
	@DisplayName("密码")
	void testPassword() {
		assertTrue(ParameterChecker.checkPassword("root123456"));
		assertTrue(ParameterChecker.checkPassword("1234567_"));
		assertTrue(ParameterChecker.checkPassword("1234567$"));
		assertTrue(ParameterChecker.checkPassword("1234567@"));
		assertTrue(ParameterChecker.checkPassword("ABCDEFDa"));
		assertTrue(ParameterChecker.checkPassword("01234567891234567a"));
		assertTrue(ParameterChecker.checkPassword("01234567891234567&"));
		assertTrue(ParameterChecker.checkPassword("~!@#$%^&*._1234"));
		
		assertFalse(ParameterChecker.checkPassword(""));
		assertFalse(ParameterChecker.checkPassword("123456"));
		assertFalse(ParameterChecker.checkPassword("abc"));
		assertFalse(ParameterChecker.checkPassword("12345678"));
		assertFalse(ParameterChecker.checkPassword("012345678912345678a"));
		assertFalse(ParameterChecker.checkPassword("01234567891234567["));
		assertFalse(ParameterChecker.checkPassword(null));
	}
	@Test
	@DisplayName("姓名")
	void testName() {
		assertTrue(ParameterChecker.checkName("王"));
		assertTrue(ParameterChecker.checkName("张三"));
		assertTrue(ParameterChecker.checkName("Bell"));
		assertTrue(ParameterChecker.checkName("a"));
		assertTrue(ParameterChecker.checkName("abcdefghij"));
		
		assertFalse(ParameterChecker.checkName(""));
		assertFalse(ParameterChecker.checkName("abcdefghijk"));
		assertFalse(ParameterChecker.checkName(null));
	}
	@Test
	@DisplayName("联系电话")
	void testPhone() {
		assertTrue(ParameterChecker.checkPhone("+8611111111111"));
		assertTrue(ParameterChecker.checkPhone("12345678901"));
		assertTrue(ParameterChecker.checkPhone("10086"));
		
		assertFalse(ParameterChecker.checkPhone(null));
		assertFalse(ParameterChecker.checkPhone("a1231564654"));
		assertFalse(ParameterChecker.checkPhone(""));
	}
	@Test
	@DisplayName("线路代码")
	void testRouteCode() {
		assertTrue(ParameterChecker.checkRouteCode("10086"));
		assertTrue(ParameterChecker.checkRouteCode("1234567890"));
		assertTrue(ParameterChecker.checkRouteCode("100a"));

		assertFalse(ParameterChecker.checkRouteCode(null));
		assertFalse(ParameterChecker.checkRouteCode(""));
		assertFalse(ParameterChecker.checkRouteCode("12345678901"));
		assertFalse(ParameterChecker.checkRouteCode("测试"));
	}
	@Test
	@DisplayName("备注")
	void testComment() {
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < 200; i++) {
			t.append('a');
		}
		String temp = t.toString();
		assertTrue(ParameterChecker.checkComment(""));
		assertTrue(ParameterChecker.checkComment(temp));
		
		
		assertFalse(ParameterChecker.checkComment(null));
		assertFalse(ParameterChecker.checkComment(temp + "a"));
	}
}
