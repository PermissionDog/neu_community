package com.github.permissiondog.community.model.enumeration;

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
