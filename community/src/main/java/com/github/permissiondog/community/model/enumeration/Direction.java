package com.github.permissiondog.community.model.enumeration;

/**
 * 运行方向
 * 
 * @author PermissionDog
 *
 */
public enum Direction {
	UP {
		@Override
		public String toString() {
			return "上行";
		}
	},
	DOWN {
		@Override
		public String toString() {
			return "下行";
		}
	}
}
