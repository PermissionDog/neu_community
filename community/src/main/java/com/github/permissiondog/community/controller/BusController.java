package com.github.permissiondog.community.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.exception.AlreadyReservedException;
import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchBusException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.exception.NotReservedException;
import com.github.permissiondog.community.exception.OutOfServiceException;
import com.github.permissiondog.community.exception.RouteCodeAlreadyExistException;
import com.github.permissiondog.community.exception.TimeExceededException;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.service.BusService;
import com.github.permissiondog.community.service.MemberService;
import com.github.permissiondog.community.service.impl.BusServiceImpl;
import com.github.permissiondog.community.service.impl.MemberServiceImpl;
import com.github.permissiondog.community.view.ModifyBusFrame;
import com.github.permissiondog.community.view.NewBusFrame;
import com.github.permissiondog.community.view.PassengerListFrame;
import com.github.permissiondog.community.view.SetExpireTimeFrame;

/**
 * 班车 Controller
 * 
 * @author PermissionDog
 *
 */
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
	 * @param bus 班车信息
	 * @return 成功返回班车, 失败返回 null
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
	 * @param bus 班车信息
	 * @return 修改成功返回被修改的班车, 失败返回 null
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
	 * @param id 班车ID
	 * @return 找到返回班车, 失败返回 null
	 */
	public Bus getBus(int id) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getBus(id);
	}

	/**
	 * 获取班车
	 * 
	 * @param code 路线代码
	 * @return 找到返回班车, 失败返回 null
	 */
	public Bus getBus(String code) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getBus(code);
	}

	/**
	 * 删除班车
	 * 
	 * @param id 班车ID
	 * @return 删除成功返回被删除的班车, 失败返回 null
	 */
	public Bus deleteBus(int id) {
		BusService busService = BusServiceImpl.getInstance();
		return busService.deleteBus(id);
	}

	/**
	 * 登记班车
	 * 
	 * @param busID    班车ID
	 * @param memberID 乘客ID
	 * @return 登记成功返回班车, 失败返回 null
	 */
	public Bus reserveBus(int busID, int memberID) {
		BusService busService = BusServiceImpl.getInstance();
		try {
			return busService.reserveBus(busID, memberID);
		} catch (NoSuchBusException e) {
			JOptionPane.showMessageDialog(null, "未找到班车", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (NoSuchMemberException e) {
			JOptionPane.showMessageDialog(null, "未找到老人", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (AlreadyReservedException e) {
			JOptionPane.showMessageDialog(null, "该乘客已预订", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (OutOfServiceException e) {
			JOptionPane.showMessageDialog(null, "今天不在运营日期内", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (TimeExceededException e) {
			JOptionPane.showMessageDialog(null, "今日已错过时间, 无法预定", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * 取消登记
	 * 
	 * @param busID    班车ID
	 * @param memberID 乘客ID
	 * @return 取消登记成功返回班车, 失败返回 null
	 */
	public Bus removeReservation(int busID, int memberID) {
		BusService busService = BusServiceImpl.getInstance();
		try {
			return busService.removeReservation(busID, memberID);
		} catch (NoSuchBusException e) {
			JOptionPane.showMessageDialog(null, "未找到班车", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (NoSuchMemberException e) {
			JOptionPane.showMessageDialog(null, "未找到老人", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (NotReservedException e) {
			JOptionPane.showMessageDialog(null, "乘客尚未预约该班车", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * 获取所有班车
	 * 
	 * @return 所有班车信息
	 */
	public List<Bus> getAllBuses() {
		BusService busService = BusServiceImpl.getInstance();
		return busService.getAllBuses();
	}

	/**
	 * 获取班车乘客
	 * 
	 * @param id
	 */
	public List<Member> getPassengers(int id) {
		BusService busService = BusServiceImpl.getInstance();
		MemberService memberService = MemberServiceImpl.getInstance();
		Bus bus = busService.getBus(id);
		if (bus == null) {
			return Collections.emptyList();
		}

		return Arrays.asList(bus.getPassengers().stream().map(passengerID -> memberService.getMember(passengerID))
				.toArray(Member[]::new));
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
	 * @param id 班车ID
	 */
	public void showModifyBusFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new ModifyBusFrame(id).setVisible(true);
		});
	}

	/**
	 * 显示设置班车截止时间界面
	 * 
	 * @param id 班车ID
	 */
	public void showSetExpireTimeFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new SetExpireTimeFrame(id).setVisible(true);
		});
	}

	/**
	 * 显示乘客列表界面
	 * 
	 * @param id 班车ID
	 */
	public void showPassengerListFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new PassengerListFrame(id).setVisible(true);
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
