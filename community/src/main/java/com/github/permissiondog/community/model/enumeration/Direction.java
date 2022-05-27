package com.github.permissiondog.community.model.enumeration;

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
