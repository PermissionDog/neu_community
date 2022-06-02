package com.github.permissiondog.community.model.dao;

import com.github.permissiondog.community.model.dao.exception.UnsupportedDao;
import com.github.permissiondog.community.model.dao.impl.BusDaoImpl;
import com.github.permissiondog.community.model.dao.impl.MemberDaoImpl;
import com.github.permissiondog.community.model.dao.impl.UserDaoImpl;

/**
 * Dao 的工厂类
 * 
 * @author PermissionDog
 *
 */
public class Dao {
	public static final String USER = "user";
	public static final String MEMBER = "member";
	public static final String BUS = "bus";
	
	/**
	 * 获取 Dao 对象
	 * 
	 * @param name	Dao 名称
	 * @return		Dao
	 * @throws UnsupportedDao	Dao 名称未找到
	 * @see Dao#USER
	 * @see Dao#MEMBER
	 * @see Dao#BUS
	 */
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
