package com.github.permissiondog.community.model.dao;

/**
 * 观察者接口
 * 
 * @author PermissionDog
 *
 */
public interface Observer {
	/**
	 * 	数据修改时会调用
	 */
	public void onChanged();
}
