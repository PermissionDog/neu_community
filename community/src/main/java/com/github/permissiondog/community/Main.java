package com.github.permissiondog.community;

import java.io.IOException;
import java.nio.file.*;

import com.github.permissiondog.community.util.FileUtil;
import com.github.permissiondog.community.view.LoginFrame;

public class Main {
	public static void main(String[] args) {
		initFile();
		
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
	}
	public static void initFile() {
		Path dataPath = Paths.get(Constants.DATA_FOLDER);
		if (!Files.exists(dataPath)) {
			try {
				Files.createDirectories(dataPath);
			} catch (IOException e) {
				System.out.println("���������ļ��г���");
				e.printStackTrace();
				System.exit(1);
			}
		}
		if (!Files.isDirectory(dataPath)) {
			System.out.println("�����ļ����ѱ�ռ��");
			System.exit(1);
		}
		
		Path userTablePath = Paths.get(Constants.USER_TABLE_NAME);
		if (!Files.exists(userTablePath)) {
			FileUtil.writeResource("/com/github/permissiondog/community/resources/users.json", Constants.USER_TABLE_NAME);
		}
	}
}
