package com.github.permissiondog.community.service;

import java.util.List;

import com.github.permissiondog.community.exception.AlreadyReservedException;
import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchBusException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.exception.NotReservedException;
import com.github.permissiondog.community.exception.OutOfServiceException;
import com.github.permissiondog.community.exception.RouteCodeAlreadyExistException;
import com.github.permissiondog.community.exception.TimeExceededException;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.dao.Observer;

public interface BusService {

	/**
	 * 创建
	 * 
	 * @param bus 班车信息
	 * @return	成功返回班车
	 */
	public Bus newBus(Bus bus) throws RouteCodeAlreadyExistException, IllegalParameterException;
	
	/**
	 * 删除班车
	 * 
	 * @param id	班车ID
	 * @return		成功返回被删除的班车, 未找到返回 null
	 */
	public Bus deleteBus(int id);
	
	
	/**
	 * 获取班车
	 * 
	 * @param id	班车ID
	 * @return		成功返回班车, 未找到返回 null
	 */
	public Bus getBus(int id);
	
	/**
	 * 获取班车
	 * 
	 * @param code	班车代码
	 * @return		成功返回班车, 未找到返回 null
	 */
	public Bus getBus(String code);
	
	/**
	 * 修改班车
	 * 
	 * @param bus	班车信息
	 * @return		成功返回班车, 失败返回 null
	 * @throws NoSuchBusException			未找到班车
	 * @throws IllegalParameterException	参数错误
	 */
	public Bus modifyBus(Bus bus) throws IllegalParameterException, NoSuchBusException;
	
	/**
	 * 预定班车
	 * 
	 * @param busID		班车ID
	 * @param memberID	乘客ID
	 * @return			成功返回班车, 失败返回 null
	 * @throws NoSuchBusException		班车未找到
	 * @throws NoSuchMemberException	入住人未找到
	 * @throws TimeExceededException	超过当天截止时间, 如果未设置截止时间, 则按照发车时间
	 * @throws OutOfServiceException	当天不在运营日期
	 * @throws AlreadyReservedException	乘客已经在当前车辆预定过了
	 */
	public Bus reserveBus(int busID, int memberID) throws NoSuchBusException, NoSuchMemberException, TimeExceededException, OutOfServiceException, AlreadyReservedException;
	
	/**
	 * 取消班车预定
	 * 
	 * @param busID		班车ID
	 * @param memberID	乘客ID
	 * @return			成功返回班车
	 * @throws NoSuchBusException		班车未找到
	 * @throws NoSuchMemberException	入住人未找到
	 * @throws NotReservedException		乘客不在该班车内
	 */
	public Bus removeReservation(int busID, int memberID) throws NoSuchBusException, NoSuchMemberException, NotReservedException;
	
	/**
	 * 获取所有班车
	 * 
	 * @return	班车列表
	 */
	public List<Bus> getAllBuses();
	
	/**
	 * 注册观察者
	 * 
	 * @param o	观察者
	 */
	public void registerObserver(Observer o);
}
