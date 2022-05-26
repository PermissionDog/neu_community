package com.github.permissiondog.community.controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.*;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.service.impl.UserServiceImpl;
import com.github.permissiondog.community.view.AdminMainFrame;
import com.github.permissiondog.community.view.HouseKeeperMainFrame;
import com.github.permissiondog.community.view.LogisticsManagerMainFrame;
import com.github.permissiondog.community.view.MainFrame;
import com.github.permissiondog.community.view.RegisterFrame;

public class UserController {
	
	private static UserController userController;
	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
	private UserController() {}
	
	private MainFrame mainFrame;
	
	public List<User> getAllUsers() {
		UserService userService = UserServiceImpl.getInstance();
		return userService.getAllUsers();
	}
	
	public User register(String username, String pwd, String name, Gender gender, LocalDate birthday, String phone, Role role) {
		UserService userService = UserServiceImpl.getInstance();
		return userService.register(username, pwd, name, gender, birthday, phone, role);
	}
	
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
		
		SwingUtilities.invokeLater(() -> {
			switch (user.getRole()) {
				case ADMINISTRATOR:
					mainFrame = new AdminMainFrame(user);
					break;
				case HOUSEKEEPER:
					mainFrame = new HouseKeeperMainFrame(user);
					break;
				default:
					mainFrame = new LogisticsManagerMainFrame(user);
			}
			mainFrame.setVisible(true);
		});
		
	}
	public void showRegisterFrame() {
		SwingUtilities.invokeLater(() -> {
			RegisterFrame rf = new RegisterFrame();
			rf.setVisible(true);
		});
	}
}
