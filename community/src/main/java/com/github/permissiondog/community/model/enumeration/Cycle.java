package com.github.permissiondog.community.model.enumeration;

import java.time.DayOfWeek;

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
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.MONDAY;
		}
	},
	TUESDAY {
		@Override
		public String toString() {
			return "星期二";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.TUESDAY;
		}
	},
	WEDNESDAY {
		@Override
		public String toString() {
			return "星期三";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.WEDNESDAY;
		}
	},
	THURSDAY {
		@Override
		public String toString() {
			return "星期四";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.THURSDAY;
		}
	},
	FRIDAY {
		@Override
		public String toString() {
			return "星期五";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.FRIDAY;
		}
	},
	SATURDAY {
		@Override
		public String toString() {
			return "星期六";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.SATURDAY;
		}
	},
	SUNDAY {
		@Override
		public String toString() {
			return "星期日";
		}
		@Override
		public DayOfWeek toDayOfWeek() {
			return DayOfWeek.SUNDAY;
		}
	};
	public DayOfWeek toDayOfWeek() {
		return null;
	}
}
