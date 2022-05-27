package com.github.permissiondog.community.service;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;
import com.github.permissiondog.community.model.User;

public interface UserService {
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
	public User login(String username, String password) throws WrongPasswordException, NoSuchUserException;
	
	/**
	 * 注册
	 * 
	 * @param username	用户名
	 * @param pwd		密码
	 * @param name		姓名
	 * @param gender	性别
	 * @param birthday	生日
	 * @param phone		联系方式
	 * @param role		权限
	 * @return			成功返回用户, 失败返回 null
	 */
	public User register(String username, String pwd, String name, Gender gender, LocalDate birthday, String phone, Role role);
	/**
	 * 删除用户
	 * 
	 * @param id	用户ID
	 * @return		成功返回被删除的用户, 失败返回 null
	 */
	public User deleteUser(int id);
	/**
	 * 获取用户
	 * 
	 * @param id	用户ID
	 * @return		返回用户, 失败返回 null
	 */
	public User getUser(int id);
	
	/**
	 * 获取用户
	 * 
	 * @param username	用户ID
	 * @return			返回用户, 失败返回 null
	 */
	public User getUser(String username);
	/**
	 * 修改用户
	 * 
	 * @param user	用户信息
	 * @return		成功返回用户信息, 失败返回 null
	 */
	public User modifyUser(User user);

	/**
	 *	获取用户列表
	 * 
	 *	@return 返回所有用户
	 */
	public List<User> getAllUsers();
	/**
	 * 获取用户列表
	 * 
	 * @param role	权限
	 * @return		指定权限的用户列表
	 */
	public List<User> getAllUsers(Role role);
}
