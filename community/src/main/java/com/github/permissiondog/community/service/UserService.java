package com.github.permissiondog.community.service;

import java.util.List;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.UserNameAlreadyExistException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.Observer;

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
	 * @param user 用户
	 * 
	 * @throws IllegalParameterException		参数错误
	 * @throws UserNameAlreadyExistException	用户名已存在 
	 * 
	 * @return			成功返回用户, 失败返回 null
	 */
	public User register(User user) throws UserNameAlreadyExistException, IllegalParameterException ;
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
	 * 
	 * @throws IllegalParameterException	参数错误
	 * @throws NoSuchUserException			用户不存在
	 * 
	 * @return		成功返回用户信息, 失败返回 null
	 */
	public User modifyUser(User user) throws IllegalParameterException, NoSuchUserException;

	/**
	 *	获取用户列表
	 * 
	 *	@return 返回所有用户
	 */
	public List<User> getAllUsers();

	/**
	 * 注册观察者
	 * 
	 * @param o	观察者
	 */
	public void registerObserver(Observer o);
}
