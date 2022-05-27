package com.github.permissiondog.community.controller;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.Callback;
import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.exception.UserNameAlreadyExistException;
import com.github.permissiondog.community.exception.WrongPasswordException;
import com.github.permissiondog.community.model.*;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Role;
import com.github.permissiondog.community.service.UserService;
import com.github.permissiondog.community.service.impl.UserServiceImpl;
import com.github.permissiondog.community.view.AdminMainFrame;
import com.github.permissiondog.community.view.HouseKeeperMainFrame;
import com.github.permissiondog.community.view.LogisticsManagerMainFrame;
import com.github.permissiondog.community.view.MainFrame;
import com.github.permissiondog.community.view.ModifyUserFrame;
import com.github.permissiondog.community.view.RegisterFrame;

public class UserController {

	private static UserController userController;

	public static UserController getInstance() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}

	private UserController() {
	}

	private MainFrame mainFrame;

	/**
	 * 获取所有用户
	 * 
	 * @return 用户列表
	 */
	public List<User> getAllUsers() {
		UserService userService = UserServiceImpl.getInstance();
		return userService.getAllUsers();
	}

	/**
	 * 注册
	 * 
	 * @param username 用户名
	 * @param pwd      明文密码
	 * @param name     姓名
	 * @param gender   性别
	 * @param birthday 生日
	 * @param phone    联系电话
	 * @param role     权限
	 * @return 成功返回用户
	 */
	public User register(String username, String pwd, String name, Gender gender, LocalDate birthday, String phone,
			Role role) {
		UserService userService = UserServiceImpl.getInstance();
		User u;
		try {
			u = userService.register(username, pwd, name, gender, birthday, phone, role);
		} catch (UserNameAlreadyExistException e) {
			JOptionPane.showMessageDialog(null, "用户名已存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		return u;
	}

	/**
	 * 登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param jf       登录窗口
	 */
	public void login(String username, String password, JFrame jf) {
		UserService userService = UserServiceImpl.getInstance();
		User user;
		try {
			user = userService.login(username, password);
		} catch (NoSuchUserException e) {
			JOptionPane.showMessageDialog(jf, "用户名错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (WrongPasswordException e) {
			JOptionPane.showMessageDialog(jf, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		jf.dispose();

		SwingUtilities.invokeLater(() -> {
			switch (user.getRole()) {
			case ADMINISTRATOR:
				mainFrame = new AdminMainFrame(user);
				break;
			case HOUSEKEEPER:
				mainFrame = new HouseKeeperMainFrame(user);
				break;
			default:
				mainFrame = new LogisticsManagerMainFrame(user);
			}
			mainFrame.setVisible(true);
		});

	}

	/**
	 * 获取用户
	 * 
	 * @param username 用户名
	 * @return 用户
	 */
	public User getUser(String username) {
		UserService userService = UserServiceImpl.getInstance();
		return userService.getUser(username);
	}

	/**
	 * 获取用户
	 * 
	 * @param id 用户ID
	 * @return 用户
	 */
	public User getUser(int id) {
		UserService userService = UserServiceImpl.getInstance();
		return userService.getUser(id);
	}

	/**
	 * 删除用户
	 * 
	 * @param id 要删除的用户ID
	 * @return 删除的用户, 失败返回 null
	 */
	public User deleteUser(int id) {
		UserService userService = UserServiceImpl.getInstance();
		return userService.deleteUser(id);
	}

	/**
	 * 修改用户
	 * 
	 * @param u	要修改的用户信息
	 * @return	修改的用户, 失败返回null
	 */
	public User modifyUser(User u) {
		UserService userService = UserServiceImpl.getInstance();
		try {
			u = userService.modifyUser(u);
		} catch (NoSuchUserException e) {
			JOptionPane.showMessageDialog(null, "用户不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		return u;
		
	}

	/**
	 * 显示注册窗口
	 * 
	 * @param cb 注册成功调用的回调函数
	 */
	public void showRegisterFrame(Callback cb) {
		SwingUtilities.invokeLater(() -> {
			RegisterFrame rf = new RegisterFrame(cb);
			rf.setVisible(true);
		});
	}

	/**
	 * 显示修改用户窗口
	 * 
	 * @param id 要修改的用户
	 * @param cb 修改成功调用的回调函数
	 */
	public void showModifyUserFrame(int id, Callback cb) {
		SwingUtilities.invokeLater(() -> {
			ModifyUserFrame muf = new ModifyUserFrame(id, cb);
			muf.setVisible(true);
		});
	}

}
