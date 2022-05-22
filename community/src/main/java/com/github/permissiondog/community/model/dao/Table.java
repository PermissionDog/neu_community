package com.github.permissiondog.community.model.dao;

import java.util.*;

/**
 * JSON����ʵ����
 * 
 * @author PermissionDog
 *
 * @param <T> �������
 */
public class Table<T> {
	private int idCount;	//ID����ֵ����
	private Collection<T> data;	//��
	
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
