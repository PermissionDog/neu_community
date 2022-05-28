package com.github.permissiondog.community.service.impl;

import com.github.permissiondog.community.exception.WrongPasswordException;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.Config;
import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.UserNameAlreadyExistException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.*;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Role;
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
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		User u = userDao.find(username);
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
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		return userDao.getAll();
	}

	@Override
	public User register(User u) throws UserNameAlreadyExistException, IllegalParameterException {
		String username = u.getUsername();
		String name = u.getName();
		String pwd = u.getPassword();
		Gender gender = u.getGender();
		LocalDate birthday = u.getBirthday();
		String phone = u.getPhone();
		Role role = u.getRole();
		
		ParameterChecker.ensureNotEmpty(username, "用户名");
		ParameterChecker.ensureNotEmpty(pwd, "密码");
		ParameterChecker.ensureNotEmpty(name, "姓名");
		ParameterChecker.ensureNotEmpty(gender, "性别");
		ParameterChecker.ensureNotEmpty(birthday, "生日");
		ParameterChecker.ensureNotEmpty(phone, "联系方式");
		ParameterChecker.ensureNotEmpty(role, "权限");

		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		if (userDao.find(username) != null) {
			throw new UserNameAlreadyExistException();
		}

		// 参数校验
		ParameterChecker.ensure(ParameterChecker.checkUserName(username), "用户名由4-18位英文字母、数字或下划线组成");
		ParameterChecker.ensure(ParameterChecker.checkPassword(pwd), "密码由8-18位字符组成，至少含有大写字母、小写字母、数字、特殊符号（~!@#$%^&*._）中的任意两种");
		ParameterChecker.ensure(ParameterChecker.checkName(name), "姓名由1-10位英文字母、汉字或数字组成");
		ParameterChecker.ensure(ParameterChecker.checkPhone(phone), "电话由5-20位数字或加号组成");

		User user = new User();
		user.setUsername(username);
		user.setPassword(Encrypt.encryptPassword(Config.getConfig().getPasswordAlgorithm(), pwd));
		user.setName(name);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setPhone(phone);
		user.setRole(role);

		return userDao.insert(user);
	}

	@Override
	public User modifyUser(User user) throws IllegalParameterException, NoSuchUserException {
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		User oldUser = userDao.find(user.getId());
		if (oldUser == null) {
			throw new NoSuchUserException();
		}
		
		if (user.getPassword() == null || user.getPassword() == "") {
			user.setPassword(oldUser.getPassword());
		} else {
			ParameterChecker.ensure(ParameterChecker.checkPassword(user.getPassword()), "密码由8-18位字符组成，至少含有大写字母、小写字母、数字、特殊符号（~!@#$%^&*._）中的任意两种");
			user.setPassword(Encrypt.encryptPassword(Config.getConfig().getPasswordAlgorithm(), user.getPassword()));
		}
		ParameterChecker.ensureNotEmpty(user.getUsername(), "用户名");
		ParameterChecker.ensureNotEmpty(user.getName(), "姓名");
		ParameterChecker.ensureNotEmpty(user.getGender(), "性别");
		ParameterChecker.ensureNotEmpty(user.getBirthday(), "生日");
		ParameterChecker.ensureNotEmpty(user.getPhone(), "联系方式");
		ParameterChecker.ensureNotEmpty(user.getRole(), "权限");
		
		ParameterChecker.ensure(ParameterChecker.checkUserName(user.getUsername()), "用户名由4-18位英文字母、数字或下划线组成");
		ParameterChecker.ensure(ParameterChecker.checkName(user.getName()), "姓名由1-10位英文字母、汉字或数字组成");
		ParameterChecker.ensure(ParameterChecker.checkPhone(user.getPhone()), "电话由5-20位数字或加号组成");
		
		//如果是生活管家, 去除关联的老人信息
		if (oldUser.getRole().equals(Role.HOUSEKEEPER) && !oldUser.getRole().equals(user.getRole())) {
			MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
			List<Member> members = memberDao.getAll();
			members.stream().filter(member -> {
				return member.getHouseKeeperID() == oldUser.getId();
			}).forEach(member -> {
				member.setHouseKeeperID(-1);
				memberDao.update(member);
			});
		}
		
		return userDao.update(user);
	}


	@Override
	public User deleteUser(int id) {
		UserDao userDao = (UserDao) Dao.of(Dao.USER); 
		User u = userDao.find(id);
		//如果是生活管家, 去除关联的老人信息
		if (u.getRole().equals(Role.HOUSEKEEPER)) {
			MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
			List<Member> members = memberDao.getAll();
			members.stream().filter(member -> {
				return member.getHouseKeeperID() == u.getId();
			}).forEach(member -> {
				member.setHouseKeeperID(-1);
				memberDao.update(member);
			});
		}
		
		return userDao.delete(id);
	}

	@Override
	public User getUser(int id) {
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		return userDao.find(id);
	}

	@Override
	public User getUser(String username) {
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		return userDao.find(username);
	}

	@Override
	public List<User> getAllUsers(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void registerObserver(Observer o) {
		UserDao userDao = (UserDao) Dao.of(Dao.USER);
		userDao.registerObserver(o);
	}

}
