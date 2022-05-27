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

	/**
	 * 从内存中加载用户数据
	 */
	private void loadUsers() {
		users = new HashMap<>();
		String userJson = FileUtil.readFile(Constants.USER_TABLE_NAME);
		Table<User> userTable = GsonUtil.gson.fromJson(userJson, new TypeToken<Table<User>>() {
		}.getType());
		idCount = userTable.getIdCount();
		for (User u : userTable.getData()) {
			users.put(u.getId(), u);
		}
	}

	/**
	 * 将内存中的用户数据保存至文件
	 */
	private void saveUsers() {
		Table<User> userTable = new Table<>();
		userTable.setIdCount(idCount);
		userTable.setData(users.values());
		String userJson = GsonUtil.gson.toJson(userTable);
		FileUtil.writeFile(Constants.USER_TABLE_NAME, userJson);
	}

	
	@Override
	public User findUserByUserName(String username) {
		for (User u : users.values()) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}


	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(users.values());
	}

	@Override
	public User insertUser(User user) {
		user.setId(idCount++);
		users.put(user.getId(), user);
		saveUsers();

		return user;
	}

	@Override
	public User deleteUser(int id) {
		User u = users.remove(id);
		if (u != null)
			saveUsers();

		return u;
	}

	@Override
	public User updateUser(User user) {
		if (!users.containsKey(user.getId())) {
			return null;
		}
		users.put(user.getId(), user);
		saveUsers();
		return user;
	}

}
