package com.github.permissiondog.community.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 密码加密相关测试
 * 
 * @author PermissionDog
 *
 */
public class EncryptTest {
	@Test
	@DisplayName("测试加密")
	void TestEncrypt() {
		String password = "abcd";
		assertTrue(Encrypt.check(password, Encrypt.encryptPassword("MD5", password)));
		assertTrue(Encrypt.check(password, Encrypt.encryptPassword("SHA-256", password)));
		assertTrue(Encrypt.check(password, Encrypt.encryptPassword("SHA-512", password)));
		assertTrue(Encrypt.check(password, Encrypt.encryptPassword("PLAINTEXT", password)));
		assertNotEquals(Encrypt.encryptPassword("MD5", password), Encrypt.encryptPassword("MD5", password));
		assertNotEquals(Encrypt.encryptPassword("SHA-512", password), Encrypt.encryptPassword("SHA-512", password));
		assertEquals("PLAINTEXT::" + password, Encrypt.encryptPassword("PLAINTEXT", password));
	}
}
