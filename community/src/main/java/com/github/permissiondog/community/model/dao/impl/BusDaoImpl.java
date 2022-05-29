package com.github.permissiondog.community.model.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.Table;
import com.github.permissiondog.community.model.dao.BusDao;
import com.github.permissiondog.community.util.FileUtil;
import com.github.permissiondog.community.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class BusDaoImpl extends BaseDaoImpl<Bus> implements BusDao {
	private static BusDao busDao;
	
	public static BusDao getInstance() {
		if (busDao == null) {
			busDao = new BusDaoImpl();
		}
		return busDao;
	}
	
	private BusDaoImpl() {
		load();
	}
	
	private Map<Integer, Bus> buses;
	private int idCount;
	
	@Override
	public int getIdCount() {
		return idCount;
	}

	@Override
	public int getAndIncreaseIdCount() {
		return ++idCount;
	}
	
	@Override
	public Map<Integer, Bus> getData() {
		return buses;
	}
	
	@Override
	public void load() {
		buses = new HashMap<>();
		String json = FileUtil.readFile(Constants.BUS_TABLE_NAME);
		Table<Bus> table = GsonUtil.gson.fromJson(json, new TypeToken<Table<Bus>>() {
		}.getType());
		idCount = table.getIdCount();
		for (Bus b : table.getData()) {
			buses.put(b.getId(), b);
		}
	}

	@Override
	public void save() {
		Table<Bus> table = new Table<>();
		table.setIdCount(idCount);
		table.setData(buses.values());
		String json = GsonUtil.gson.toJson(table);
		FileUtil.writeFile(Constants.BUS_TABLE_NAME, json);
	}

	@Override
	public Bus find(String code) {
		for (Bus b : buses.values()) {
			if (b.getCode().equals(code)) {
				return b;
			}
		}
		return null;
	}


}
