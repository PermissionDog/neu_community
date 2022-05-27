package com.github.permissiondog.community;

import java.time.format.DateTimeFormatter;

public class Constants {
	public static final String CONFIG_FILE = "config.json";
	
	public static final String DATA_FOLDER = "data";
	
	public static final String USER_TABLE_NAME = DATA_FOLDER + "/users.json";
	
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-dd");
	
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
}
