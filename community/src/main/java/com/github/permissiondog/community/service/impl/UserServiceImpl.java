package com.github.permissiondog.community.service.impl;

import com.github.permissiondog.community.exception.WrongPasswordException;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.Config;
import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.UserNameAlreadyExistException;
import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.*;
import com.github.permissiondog.community.model.dao.impl.UserDaoImpl;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.util.Encrypt;

public class UserServiceImpl implements UserService {
	private static UserService userService;

	private UserServiceImpl() {
	}

	public static UserService getInstance() {
		if (userService == null) {
			userService = new UserServiceImpl();
		}
		return userService;
	}

	@Override
	public User login(String username, String password) throws WrongPasswordException, NoSuchUserException {
		UserDao userDao = UserDaoImpl.getInstance();
		User u = userDao.findUserByUserName(username);
		// 判断是否找到该用户
		if (u == null) {
			throw new NoSuchUserException();
		}
		// 判断密码哈希值是否一致
		if (!Encrypt.check(password, u.getPassword())) {
			throw new WrongPasswordException();
		}
		return u;
	}

	@Override
	public List<User> getAllUsers() {
		UserDao userDao = UserDaoImpl.getInstance();
		return userDao.getAllUsers();
	}

	@Override
	public User register(String username, String pwd, String name, Gender gender, LocalDate birthday, String phone,
			Role role) throws UserNameAlreadyExistException, IllegalParameterException {
		ensureNotEmpty(username, "用户名");
		ensureNotEmpty(pwd, "密码");
		ensureNotEmpty(name, "姓名");
		ensureNotEmpty(gender, "性别");
		ensureNotEmpty(birthday, "生日");
		ensureNotEmpty(phone, "联系方式");
		ensureNotEmpty(role, "权限");

		UserDao userDao = UserDaoImpl.getInstance();
		if (userDao.findUserByUserName(username) != null) {
			throw new UserNameAlreadyExistException();
		}

		// 参数校验
		ensure(ParameterChecker.checkUserName(username), "用户名由4-18位英文字母、数字或下划线组成");
		ensure(ParameterChecker.checkPassword(pwd), "密码由8-18位字符组成，至少含有大写字母、小写字母、数字、特殊符号（~!@#$%^&*._）中的任意两种");
		ensure(ParameterChecker.checkName(name), "姓名由1-10位英文字母、汉字或数字组成");
		ensure(ParameterChecker.checkPhone(phone), "电话由5-20位数字或加号组成");

		User user = new User();
		user.setUsername(username);
		user.setPassword(Encrypt.encryptPassword(Config.getConfig().getPasswordAlgorithm(), pwd));
		user.setName(name);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setPhone(phone);
		user.setRole(role);

		return userDao.insertUser(user);
	}

	@Override
	public User modifyUser(User user) throws IllegalParameterException, NoSuchUserException {
		UserDao userDao = UserDaoImpl.getInstance();
		User oldUser = userDao.findUserByID(user.getId());
		if (oldUser == null) {
			throw new NoSuchUserException();
		}
		
		if (user.getPassword() == null || user.getPassword() == "") {
			user.setPassword(oldUser.getPassword());
		} else {
			ensure(ParameterChecker.checkPassword(user.getPassword()), "密码由8-18位字符组成，至少含有大写字母、小写字母、数字、特殊符号（~!@#$%^&*._）中的任意两种");
			user.setPassword(Encrypt.encryptPassword(Config.getConfig().getPasswordAlgorithm(), user.getPassword()));
		}
		ensureNotEmpty(user.getUsername(), "用户名");
		ensureNotEmpty(user.getName(), "姓名");
		ensureNotEmpty(user.getGender(), "性别");
		ensureNotEmpty(user.getBirthday(), "生日");
		ensureNotEmpty(user.getPhone(), "联系方式");
		ensureNotEmpty(user.getRole(), "权限");
		
		ensure(ParameterChecker.checkUserName(user.getUsername()), "用户名由4-18位英文字母、数字或下划线组成");
		ensure(ParameterChecker.checkName(user.getName()), "姓名由1-10位英文字母、汉字或数字组成");
		ensure(ParameterChecker.checkPhone(user.getPhone()), "电话由5-20位数字或加号组成");
		
		return userDao.updateUser(user);
	}

	private void ensureNotEmpty(Object obj, String paraName) throws IllegalParameterException {
		if (obj == null || (obj instanceof String && "".equals((String) obj))) {
			throw new IllegalParameterException(paraName + " 不能为空");
		}
	}

	private void ensure(boolean legal, String msg) throws IllegalParameterException {
		if (!legal)
			throw new IllegalParameterException(msg);
	}

	@Override
	public User deleteUser(int id) {
		UserDao userDao = UserDaoImpl.getInstance();
		return userDao.deleteUser(id);
	}

	@Override
	public User getUser(int id) {
		UserDao userDao = UserDaoImpl.getInstance();
		return userDao.findUserByID(id);
	}

	@Override
	public User getUser(String username) {
		UserDao userDao = UserDaoImpl.getInstance();
		return userDao.findUserByUserName(username);
	}

	@Override
	public List<User> getAllUsers(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
