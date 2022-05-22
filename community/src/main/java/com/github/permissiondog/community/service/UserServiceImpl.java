package com.github.permissiondog.community.service;

import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.*;
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
	 *	��¼
	 *
	 *	@param username �û���
	 *	@param password ����
	 *
	 *	@throws NoSuchUserException �û���������
	 *	@throws WrongPasswordException �������
	 *
	 *	@return ��¼�ɹ������û�
	 */
	@Override
	public User login(String username, String password) throws WrongPasswordException, NoSuchUserException {
		UserDao userDao = UserDaoImpl.getInstance();
		User u = userDao.findUserByUserName(username);
		//�ж��Ƿ��ҵ����û�
		if (u == null) {
			throw new NoSuchUserException();
		}
		//�ж������ϣֵ�Ƿ�һ��
		if (!u.getPassword().equals(Encrypt.encryptPassword(password))) {
			throw new WrongPasswordException();
		}
		return u;
	}
	
}
