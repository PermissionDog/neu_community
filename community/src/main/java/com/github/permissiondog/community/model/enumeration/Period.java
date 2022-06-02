package com.github.permissiondog.community.model.enumeration;

/**
 * 运营时段
 * 
 * @author PermissionDog
 *
 */
public enum Period {
	MORNING {
		@Override
		public String toString() {
			return "上午";
		}
	},
	AFTERNOON {
		@Override
		public String toString() {
			return "下午";
		}
	}
}
