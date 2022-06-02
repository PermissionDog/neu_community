package com.github.permissiondog.community.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.github.permissiondog.community.exception.*;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.dao.BusDao;
import com.github.permissiondog.community.model.dao.Dao;
import com.github.permissiondog.community.model.dao.MemberDao;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.model.enumeration.*;
import com.github.permissiondog.community.service.BusService;

/**
 * 班车 Service 实现
 * 
 * @author PermissionDog
 *
 */
public class BusServiceImpl implements BusService {
	private static BusService busService;
	
	private BusServiceImpl() {
	}
	
	public static BusService getInstance() {
		if (busService == null) {
			busService = new BusServiceImpl();
		}
		return busService;
	}

	@Override
	public Bus newBus(Bus b) throws RouteCodeAlreadyExistException, IllegalParameterException {
		String code = b.getCode();
		String name = b.getName();
		RouteType type = b.getType();
		Direction direction = b.getDirection();
		Cycle cycle = b.getCycle();
		Period period = b.getPeriod();
		LocalTime departureTime = b.getDepartureTime();
		LocalTime expireTime = b.getExpireTime();
		String comment = b.getComment();
		if (comment == null) {
			comment = "";
		}
		
		ParameterChecker.ensureNotEmpty(code, "线路代码");
		ParameterChecker.ensureNotEmpty(name, "线路名称");
		ParameterChecker.ensureNotEmpty(type, "线路类型");
		ParameterChecker.ensureNotEmpty(direction, "线路方向");
		ParameterChecker.ensureNotEmpty(cycle, "运营日期");
		ParameterChecker.ensureNotEmpty(period, "运营时段");
		ParameterChecker.ensureNotEmpty(departureTime, "发车时间");

		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		if (busDao.find(code) != null) {
			throw new RouteCodeAlreadyExistException();
		}
		
		ParameterChecker.ensure(ParameterChecker.checkRouteCode(code), "线路代码由1-10位字母或数字构成");
		ParameterChecker.ensure(ParameterChecker.checkName(name), "线路名称由1-10位英文字母、汉字或数字组成");
		
		Bus bus = new Bus();
		bus.setCode(code);
		bus.setName(name);
		bus.setType(type);
		bus.setDirection(direction);
		bus.setCycle(cycle);
		bus.setPeriod(period);	
		bus.setDepartureTime(departureTime);
		bus.setExpireTime(expireTime);
		bus.setComment(comment);
		bus.setPassengers(new ArrayList<>());
		
		return busDao.insert(bus);
		
	}

	@Override
	public Bus deleteBus(int id) {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		return busDao.delete(id);
	}

	@Override
	public Bus getBus(int id) {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		return busDao.find(id);
	}
	@Override
	public Bus getBus(String code) {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		return busDao.find(code);
	}

	@Override
	public Bus modifyBus(Bus bus) throws IllegalParameterException, NoSuchBusException {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		Bus oldBus = busDao.find(bus.getId());
		if (oldBus == null) {
			throw new NoSuchBusException();
		}
		//线路代码不可修改
		bus.setCode(oldBus.getCode());
		
		ParameterChecker.ensureNotEmpty(bus.getCode(), "线路代码");
		ParameterChecker.ensureNotEmpty(bus.getName(), "线路名称");
		ParameterChecker.ensureNotEmpty(bus.getType(), "线路类型");
		ParameterChecker.ensureNotEmpty(bus.getDirection(), "线路方向");
		ParameterChecker.ensureNotEmpty(bus.getCycle(), "运营日期");
		ParameterChecker.ensureNotEmpty(bus.getPeriod(), "运营时段");
		ParameterChecker.ensureNotEmpty(bus.getDepartureTime(), "发车时间");
		
		ParameterChecker.ensure(ParameterChecker.checkRouteCode(bus.getCode()), "线路代码由1-10位字母或数字构成");
		ParameterChecker.ensure(ParameterChecker.checkName(bus.getName()), "线路名称由1-10位英文字母、汉字或数字组成");
		
		return busDao.update(bus);
	}

	@Override
	public Bus reserveBus(int busID, int memberID) throws NoSuchBusException, NoSuchMemberException,
			TimeExceededException, OutOfServiceException, AlreadyReservedException {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		
		Bus bus = busDao.find(busID);
		if (bus == null) {
			throw new NoSuchBusException();
		}
		Member member = memberDao.find(memberID);
		if (member == null) {
			throw new NoSuchMemberException();
		}
		LocalTime expireTime = bus.getExpireTime();
		if (expireTime == null) {
			expireTime = bus.getDepartureTime();
		}
		
		if (expireTime.isBefore(LocalTime.now())) {
			throw new TimeExceededException();
		}
		
		if (!bus.getCycle().equals(Cycle.EVERY_DAY) && 
				!LocalDate.now().getDayOfWeek().equals(bus.getCycle().toDayOfWeek())) {
			throw new OutOfServiceException();
		}
		if (bus.getPassengers().contains(member.getId())) {
			throw new AlreadyReservedException();
		}
		
		bus.getPassengers().add(member.getId());
		
		return busDao.update(bus);
		
	}

	@Override
	public Bus removeReservation(int busID, int memberID)
			throws NoSuchBusException, NoSuchMemberException, NotReservedException {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		
		Bus bus = busDao.find(busID);
		if (bus == null) {
			throw new NoSuchBusException();
		}
		Member member = memberDao.find(memberID);
		if (member == null) {
			throw new NoSuchMemberException();
		}

		if (!bus.getPassengers().contains(member.getId())) {
			throw new NotReservedException();
		}
		
		bus.getPassengers().remove(Integer.valueOf(memberID));
		
		return busDao.update(bus);
	}

	@Override
	public List<Bus> getAllBuses() {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		return busDao.getAll();
	}

	@Override
	public void registerObserver(Observer o) {
		BusDao busDao = (BusDao) Dao.of(Dao.BUS);
		busDao.registerObserver(o);
	}
}
