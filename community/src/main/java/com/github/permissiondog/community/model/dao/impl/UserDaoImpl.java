package com.github.permissiondog.community.model.dao.impl;

import java.util.*;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.Table;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.UserDao;
import com.github.permissiondog.community.util.*;
import com.google.gson.reflect.TypeToken;

public class UserDaoImpl implements UserDao {
	private static UserDao userDao;
	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}
	private UserDaoImpl() {
		loadUsers();
	};
	
	private Map<Integer, User> users;
	private int idCount;
	private void loadUsers() {
		users = new HashMap<>();
		String userJson = FileUtil.readFile(Constants.USER_TABLE_NAME);
		Table<User> userTable = GsonUtil.gson.fromJson(userJson, 
				new TypeToken<Table<User>>() {}.getType());
		idCount = userTable.getIdCount();
		for (User u : userTable.getData()) {
			users.put(u.getId(), u);
		}
	}
	private void saveUsers() {
		Table<User> userTable = new Table<>();
		userTable.setIdCount(idCount);
		userTable.setData(users.values());
		String userJson = GsonUtil.gson.toJson(userTable);
		FileUtil.writeFile(Constants.USER_TABLE_NAME, userJson);
	}
	
	/**
	 *	查找用户
	 *
	 *	@param username 用户名
	 *	@return 找到的用户, 如果未找到返回 null
	 */
	@Override
	public User findUserByUserName(String username) {
		for (User u : users.values()) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 *	获取所有用户
	 *
	 *	@return 所有用户
	 */
	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(users.values());
	}
	
}
