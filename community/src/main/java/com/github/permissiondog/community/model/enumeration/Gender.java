package com.github.permissiondog.community.model.enumeration;

/**
 * 性别
 * 
 * @author PermissionDog
 *
 */
public enum Gender {
	FEMALE {
		@Override
		public String toString() {
			return "女";
		}
	},
	MALE {
		@Override
		public String toString() {
			return "男";
		}
	}
}
