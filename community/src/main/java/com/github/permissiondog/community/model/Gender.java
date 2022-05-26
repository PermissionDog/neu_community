package com.github.permissiondog.community.model;

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
