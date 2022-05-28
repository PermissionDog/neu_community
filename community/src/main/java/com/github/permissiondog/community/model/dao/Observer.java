package com.github.permissiondog.community.model.dao;

public interface Observer {
	/**
	 * 	数据修改时会调用
	 */
	public void onChanged();
}
