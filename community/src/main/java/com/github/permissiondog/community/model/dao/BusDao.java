package com.github.permissiondog.community.model.dao;

import com.github.permissiondog.community.model.Bus;

public interface BusDao extends BaseDao<Bus> {
	/**
	 * 查找班车
	 * 
	 * @param code	线路代码
	 * @return	找到的班车, 未找到返回null
	 */
	public Bus find(String code);
}
