package com.github.permissiondog.community.controller;

import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.service.UserServiceImpl;

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
}
