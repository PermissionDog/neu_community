package com.github.permissiondog.community.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.permissiondog.community.model.Identifiable;
import com.github.permissiondog.community.model.dao.BaseDao;

public abstract class BaseDaoImpl<T extends Identifiable> implements BaseDao<T> {

	@Override
	public T find(int id) {
		return getData().get(id);
	}

	@Override
	public T insert(T newValue) {
		newValue.setId(getAndIncreaseIdCount());
		getData().put(newValue.getId(), newValue);
		save();
		
		return newValue;
	}

	@Override
	public T delete(int id) {
		T t = getData().remove(id);
		if (t != null)
			save();
		
		return t;
	}

	@Override
	public T update(T newValue) {
		if (!getData().containsKey(newValue.getId())) {
			return null;
		}
		getData().put(newValue.getId(), newValue);
		save();
		return newValue;
	}

	@Override
	public List<T> getAll() {
		return new ArrayList<>(getData().values());
	}

}
