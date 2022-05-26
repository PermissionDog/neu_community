package com.github.permissiondog.community.model;

public enum Cycle {
	EVERY_DAY {
		@Override
		public String toString() {
			return "每日";
		}
	},
	MONDAY {
		@Override
		public String toString() {
			return "星期一";
		}
	},
	TUESDAY {
		@Override
		public String toString() {
			return "星期二";
		}
	},
	WEDNESDAY {
		@Override
		public String toString() {
			return "星期三";
		}
	},
	THURSDAY {
		@Override
		public String toString() {
			return "星期四";
		}
	},
	FRIDAY {
		@Override
		public String toString() {
			return "星期五";
		}
	},
	SATURDAY {
		@Override
		public String toString() {
			return "星期六";
		}
	},
	SUNDAY {
		@Override
		public String toString() {
			return "星期日";
		}
	}
}
