package com.github.permissiondog.community.model.dao;

import com.github.permissiondog.community.model.dao.exception.UnsupportedDao;
import com.github.permissiondog.community.model.dao.impl.BusDaoImpl;
import com.github.permissiondog.community.model.dao.impl.MemberDaoImpl;
import com.github.permissiondog.community.model.dao.impl.UserDaoImpl;

public class Dao {
	public static final String USER = "user";
	public static final String MEMBER = "member";
	public static final String BUS = "bus";
	
	public static BaseDao<?> of(String name) throws UnsupportedDao {
		if (USER.equals(name)) {
			return UserDaoImpl.getInstance();
		}
		if (MEMBER.equals(name)) {
			return MemberDaoImpl.getInstance();
		}
		if (BUS.equals(name)) {
			return BusDaoImpl.getInstance(); 
		}
		throw new UnsupportedDao();
	}
}
