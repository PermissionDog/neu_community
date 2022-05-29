package com.github.permissiondog.community.service;

import com.github.permissiondog.community.model.dao.Observer;

public interface BaseService {
	/**
	 * 注册观察者
	 * 
	 * @param o	观察者
	 */
	public void registerObserver(Observer o);
}
