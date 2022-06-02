package com.github.permissiondog.community.model.enumeration;

/**
 * 线路类型
 * 
 * @author PermissionDog
 *
 */
public enum RouteType {
	IN {
		@Override
		public String toString() {
			return "岛内班车";
		}
	},
	OUT {
		@Override
		public String toString() {
			return "岛外班车";
		}
	}
}
