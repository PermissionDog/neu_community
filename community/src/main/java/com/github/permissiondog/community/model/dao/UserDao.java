package com.github.permissiondog.community.model.dao;

import com.github.permissiondog.community.model.User;

/**
 * 用户 Dao
 * 
 * @author PermissionDog
 *
 */
public interface UserDao extends BaseDao<User> {
	/**
	 * 查找用户
	 *
	 * @param	username 用户名
	 * @return	找到的用户, 未找到返回 null
	 */
	public User find(String username);
	
}
