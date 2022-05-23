package com.github.permissiondog.community.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.service.UserServiceImpl;
import com.github.permissiondog.community.view.AdminMainFrame;
import com.github.permissiondog.community.view.HouseKeeperMainFrame;
import com.github.permissiondog.community.view.LogisticsManagerMainFrame;
import com.github.permissiondog.community.view.MainFrame;

public class UserController {
	
	private static UserController userController;
	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	private UserController() {}
	
	public User queryLogin(String username, String password) throws NoSuchUserException, WrongPasswordException {
		UserService userService = UserServiceImpl.getInstance();
		User user = userService.login(username, password);
		return user;
	}
	
	private MainFrame mainFrame;
	
	public void login(String username, String password, JFrame jf) {
		UserService userService = UserServiceImpl.getInstance();
		User user;
		try {
			user = userService.login(username, password);
		} catch (NoSuchUserException e) {
			JOptionPane.showMessageDialog(jf, "用户名错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (WrongPasswordException e) {
			JOptionPane.showMessageDialog(jf, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		jf.dispose();
		
		switch (user.getRole()) {
			case ADMINISTRATOR:
				mainFrame = new AdminMainFrame(user, userService.getAllUsers());
				break;
			case HOUSEKEEPER:
				mainFrame = new HouseKeeperMainFrame(user);
				break;
			default:
				mainFrame = new LogisticsManagerMainFrame(user);
		}
		mainFrame.setVisible(true);
	}
}
