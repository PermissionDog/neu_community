package com.github.permissiondog.community.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * 实体类测试
 * 
 * @author PermissionDog
 *
 */
public class EntityTest {
	@Test
	void testEntity() {
		Entity a = new User();
		a.setId(1);
		Entity b = new Member();
		b.setId(1);
		assertFalse(a.equals(b));
		assertFalse(b.equals(a));
		
		a = new User();
		a.setId(1);
		b = new User();
		b.setId(1);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
	}
}
