package com.github.permissiondog.community.model.dao.impl;

import java.util.*;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.Table;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.UserDao;
import com.github.permissiondog.community.util.*;
import com.google.gson.reflect.TypeToken;

/**
 * 用户 Dao 实现
 * 
 * @author PermissionDog
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	private static UserDao userDao;

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}

	private UserDaoImpl() {
		load();
	}

	private Map<Integer, User> users;
	private int idCount;
	
	@Override
	public int getIdCount() {
		return idCount;
	}

	@Override
	public int getAndIncreaseIdCount() {
		return ++idCount;
	}
	
	@Override
	public Map<Integer, User> getData() {
		return users;
	}

	@Override
	public void load() {
		users = new HashMap<>();
		String json = FileUtil.readFile(Constants.USER_TABLE_NAME);
		Table<User> table = GsonUtil.gson.fromJson(json, new TypeToken<Table<User>>() {
		}.getType());
		idCount = table.getIdCount();
		for (User u : table.getData()) {
			users.put(u.getId(), u);
		}
	}

	@Override
	public void save() {
		Table<User> table = new Table<>();
		table.setIdCount(idCount);
		table.setData(users.values());
		String json = GsonUtil.gson.toJson(table);
		FileUtil.writeFile(Constants.USER_TABLE_NAME, json);
	}

	
	
	@Override
	public User find(String username) {
		for (User u : users.values()) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}


}
