package com.github.permissiondog.community.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchHouseKeeperException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.dao.Dao;
import com.github.permissiondog.community.model.dao.MemberDao;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.model.dao.UserDao;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Role;
import com.github.permissiondog.community.service.MemberService;

public class MemberServiceImpl implements MemberService {
	private static MemberService memberService;
	
	private MemberServiceImpl() {
	}
	
	public static MemberService getInstance() {
		if (memberService == null) {
			memberService = new MemberServiceImpl();
		}
		return memberService;
	}


	@Override
	public Member newMember(Member m)
			throws IllegalParameterException {
		String name = m.getName();
		Gender gender = m.getGender();
		LocalDate birthday = m.getBirthday();
		String phone = m.getPhone();
		
		ParameterChecker.ensureNotEmpty(name, "姓名");
		ParameterChecker.ensureNotEmpty(gender, "性别");
		ParameterChecker.ensureNotEmpty(birthday, "生日");
		ParameterChecker.ensureNotEmpty(phone, "联系方式");
		
		ParameterChecker.ensure(ParameterChecker.checkName(name), "姓名由1-10位英文字母、汉字或数字组成");
		ParameterChecker.ensure(ParameterChecker.checkPhone(phone), "电话由5-20位数字或加号组成");

		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		Member member = new Member();
		member.setName(name);
		member.setGender(gender);
		member.setBirthday(birthday);
		member.setPhone(phone);
		
		return memberDao.insert(member);
	}

	@Override
	public Member deleteMember(int id) {
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		return memberDao.delete(id);
	}

	@Override
	public Member setService(int houseKeeperID, int memberID) throws NoSuchMemberException, NoSuchHouseKeeperException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getAllMembers() {
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		return memberDao.getAll();
	}

	@Override
	public List<Member> getAllMembers(int houseKeeperID) {
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		UserDao	userDao = (UserDao) Dao.of(Dao.USER);
		User houseKeeper = userDao.find(houseKeeperID);
		if (houseKeeper == null || !houseKeeper.getRole().equals(Role.HOUSEKEEPER)) {
			return new ArrayList<>();
		}
		return memberDao.getAll().stream().filter(member -> member.getHouseKeeperID() == houseKeeperID).toList();
	}
	
	@Override
	public void registerObserver(Observer o) {
		MemberDao memberDao = (MemberDao) Dao.of(Dao.MEMBER);
		memberDao.registerObserver(o);
	}

}
