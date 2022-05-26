package com.github.permissiondog.community.service.impl;

import com.github.permissiondog.community.exception.WrongPasswordException;

import java.util.List;

import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.*;
import com.github.permissiondog.community.model.dao.impl.UserDaoImpl;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.util.Encrypt;

public class UserServiceImpl implements UserService {
	private static UserService userService;
	private UserServiceImpl() {}
	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}
	
	/**
	 *	登录
	 *
	 *	@param username 用户名
	 *	@param password 密码
	 *
	 *	@throws NoSuchUserException 用户名不存在
	 *	@throws WrongPasswordException 密码错误
	 *
	 *	@return 登录成功返回用户
	 */
	@Override
	public User login(String username, String password) throws WrongPasswordException, NoSuchUserException {
		UserDao userDao = UserDaoImpl.getInstance();
		User u = userDao.findUserByUserName(username);
		//判断是否找到该用户
		if (u == null) {
			throw new NoSuchUserException();
		}
		//判断密码哈希值是否一致
		if (!Encrypt.check(password, u.getPassword())) {
			throw new WrongPasswordException();
		}
		return u;
	}
	
	/**
	 *	获取用户列表
	 * 
	 *	@return 返回所有用户
	 */
	@Override
	public List<User> getAllUsers() {
		UserDao userDao = UserDaoImpl.getInstance();
		return userDao.getAllUsers();
	}
	
}
