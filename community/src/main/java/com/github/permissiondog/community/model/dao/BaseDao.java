package com.github.permissiondog.community.model.dao;

import java.util.List;
import java.util.Map;

import com.github.permissiondog.community.model.Identifiable;

public interface BaseDao<T extends Identifiable> {
	/**
	 * 加载文件数据到内存
	 */
	public void load();
	/**
	 * 保存内存数据到文件
	 */
	public void save();
	
	/**
	 * 获取数据
	 * 
	 * @return 数据
	 */
	public Map<Integer, T> getData();
	/**
	 * 获取当前 ID 值
	 * 
	 * @return	ID
	 */
	public int getIdCount();
	/**
	 * 获取 ID 并 +1
	 * @return	增加后的ID
	 */
	public int getAndIncreaseIdCount();
	
	/**
	 * 根据 ID 查找
	 * 
	 * @param id	要查找的 ID
	 * @return		结果
	 */
	public T find(int id);
	/**
	 * 插入
	 * 
	 * @param newValue	插入的值
	 * @return			插入成功返回插入的对象, 失败返回 null
	 */
	public T insert(T newValue);
	/**
	 * 删除
	 * 
	 * @param id	要删除的 ID
	 * @return		删除成功返回被删除的对象, 失败返回 null
	 */
	public T delete(int id);
	/**
	 * 修改
	 * 
	 * @param newValue	要修改的对象
	 * @return			修改成功返回被修改的对象, 失败返回 null
	 */
	public T update(T newValue);
	/**
	 * 获取全部对象
	 * 
	 * @return	返回全部对象
	 */
	public List<T> getAll();
}
