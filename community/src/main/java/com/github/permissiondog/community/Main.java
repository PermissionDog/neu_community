package com.github.permissiondog.community;

import java.io.IOException;
import java.nio.file.*;

import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.util.FileUtil;
import com.github.permissiondog.community.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class Main {
	public static void main(String[] args) {
		initFile();
		loadConfig();
		
		UserController.getInstance().showLoginFrame();
	}
	private static void initFile() {
		Path dataPath = Paths.get(Constants.DATA_FOLDER);
		if (!Files.exists(dataPath)) {
			try {
				Files.createDirectories(dataPath);
			} catch (IOException e) {
				System.out.println("创建数据文件夹出错");
				e.printStackTrace();
				System.exit(1);
			}
		}
		if (!Files.isDirectory(dataPath)) {
			System.out.println("数据文件夹已被占用");
			System.exit(1);
		}
		
		if (!Files.exists(Paths.get(Constants.USER_TABLE_NAME))) {
			FileUtil.writeResource("/com/github/permissiondog/community/resources/users.json", Constants.USER_TABLE_NAME);
		}
		if (!Files.exists(Paths.get(Constants.MEMBER_TABLE_NAME))) {
			FileUtil.writeResource("/com/github/permissiondog/community/resources/members.json", Constants.MEMBER_TABLE_NAME);
		}
		if (!Files.exists(Paths.get(Constants.BUS_TABLE_NAME))) {
			FileUtil.writeResource("/com/github/permissiondog/community/resources/buses.json", Constants.BUS_TABLE_NAME);
		}
		
		if (!Files.exists(Paths.get(Constants.CONFIG_FILE))) {
			FileUtil.writeResource("/com/github/permissiondog/community/resources/config.json", Constants.CONFIG_FILE);
		}
	}
	private static void loadConfig() {
		Config.config = GsonUtil.gson.fromJson(
				FileUtil.readFile(Constants.CONFIG_FILE),
				new TypeToken<Config>(){}.getType());
	}
}
