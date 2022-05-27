package com.github.permissiondog.community.model.dao;

import java.util.List;

import com.github.permissiondog.community.model.User;

public interface UserDao {
	/**
	 * 查找用户
	 *
	 * @param	username 用户名
	 * @return	找到的用户, 如果未找到返回 null
	 */
	public User findUserByUserName(String username);
	/**
	 * 获取所有用户
	 *
	 * @return	所有用户
	 */
	public List<User> getAllUsers();
	/**
	 * 新增用户
	 * 
	 * @param	user 要插入的用户
	 * @return	插入成功返回新用户, 否则返回 null
	 */
	public User insertUser(User user);
	/**
	 * 删除用户
	 * 
	 * @param	id 用户ID
	 * @return 	删除成功返回被删除的用户, 否则返回 null
	 */
	public User deleteUser(int id);
	/**
	 * 修改用户
	 * 
	 * @param user	要更新的用户
	 * @return		成功返回更新后的用户, 否则返回 null
	 */
	public User updateUser(User user);
}
