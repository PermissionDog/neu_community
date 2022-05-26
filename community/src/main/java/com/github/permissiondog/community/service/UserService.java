package com.github.permissiondog.community.service;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;
import com.github.permissiondog.community.model.User;

public interface UserService {
	public User login(String username, String password);
	public List<User> getAllUsers();
	public User register(String username, String pwd, String name, Gender gender, LocalDate birthday, String phone, Role role);
}
