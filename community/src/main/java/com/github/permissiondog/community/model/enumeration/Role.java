package com.github.permissiondog.community.model.enumeration;

/**
 * 用户权限
 * 
 * @author PermissionDog
 *
 */
public enum Role {
	//管理员
	ADMINISTRATOR {
		@Override
		public String toString() {
			return "管理员";
		}
	},
	//生活管家
	HOUSEKEEPER {
		@Override
		public String toString() {
			return "生活管家";
		}
	},
	//后勤管理
	LOGISTICS_MANAGER {
		@Override
		public String toString() {
			return "后勤管理";
		}
	},
}
