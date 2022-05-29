package com.github.permissiondog.community.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchBusException;
import com.github.permissiondog.community.exception.RouteCodeAlreadyExistException;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.service.BusService;
import com.github.permissiondog.community.service.impl.BusServiceImpl;
import com.github.permissiondog.community.view.ModifyBusFrame;
import com.github.permissiondog.community.view.NewBusFrame;

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
	 * 创建新班车
	 * 
	 * @param bus	班车信息
	 * @return		成功返回班车, 失败返回 null
	 */
	public Bus newBus(Bus bus) {
		BusService busService = BusServiceImpl.getInstance();
		try {
			return busService.newBus(bus);
		} catch (RouteCodeAlreadyExistException e) {
			JOptionPane.showMessageDialog(null, "线路代码重复", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	/**
	 * 修改班车
	 * 
	 * @param bus	班车信息
	 * @return		修改成功返回被修改的班车, 失败返回 null
	 */
	public Bus modifyBus(Bus bus) {
		BusService busService = BusServiceImpl.getInstance();
		try {
			return busService.modifyBus(bus);
		} catch (NoSuchBusException e) {
			JOptionPane.showMessageDialog(null, "未找到班车", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	/**
	 * 获取班车
	 * 
	 * @param id	班车ID
	 * @return		找到返回班车, 失败返回 null
	 */
	public Bus getBus(int id) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getBus(id);
	}
	
	/**
	 * 获取班车
	 * 
	 * @param code	路线代码
	 * @return		找到返回班车, 失败返回 null
	 */
	public Bus getBus(String code) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getBus(code);
	}
	
	/**
	 * 删除班车
	 * 
	 * @param id	班车ID
	 * @return		删除成功返回被删除的班车, 失败返回 null
	 */
	public Bus deleteBus(int id) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.deleteBus(id);
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
	 * 显示新建班车界面
	 */
	public void showNewBusFrame() {
		SwingUtilities.invokeLater(() -> {
			new NewBusFrame().setVisible(true);
		});
	}
	
	/**
	 * 显示修改班车界面
	 * 
	 * @param id	班车ID
	 */
	public void showModifyBusFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new ModifyBusFrame(id).setVisible(true);
		});
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
