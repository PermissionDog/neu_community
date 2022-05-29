package com.github.permissiondog.community.controller;

import java.util.List;

import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.service.BusService;
import com.github.permissiondog.community.service.impl.BusServiceImpl;

public class BusController {
	private static BusController busController;
	
	public static BusController getInstance() {
		if (busController == null) {
			busController = new BusController();
		}
		return busController;
	}
	
	private BusController() {
	}
	
	/**
	 * 获取所有班车
	 * 
	 * @return	所有班车信息
	 */
	public List<Bus> getAllBuses() {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getAllBuses();
	}

	/**
	 * 注册观察者
	 * 
	 * @param o 观察者
	 */
	public void registerObeserver(Observer o) {
		BusService busService = BusServiceImpl.getInstance();
		busService.registerObserver(o);
	}
}
