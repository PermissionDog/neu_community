package com.github.permissiondog.community.model;

import java.util.*;

/**
 * JSON表格的实体类
 * 
 * @author PermissionDog
 *
 * @param <T> 表格数据类型
 */
public class Table<T extends Identifiable> {
	private int idCount;	//ID自增值计数
	private Collection<T> data;	//表
	
	public int getIdCount() {
		return idCount;
	}
	public void setIdCount(int idCount) {
		this.idCount = idCount;
	}
	public Collection<T> getData() {
		return data;
	}
	public void setData(Collection<T> data) {
		this.data = data;
	}
}
