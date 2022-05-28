package com.github.permissiondog.community.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchHouseKeeperException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.dao.Dao;
import com.github.permissiondog.community.model.dao.MemberDao;
import com.github.permissiondog.community.model.enumeration.Gender;
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
	public Member newMember(String name, Gender gender, LocalDate birthday, String phone)
			throws IllegalParameterException {
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
	public Member deleteMember(int id) throws NoSuchMemberException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member setService(int houseKeeperID, int memberID) throws NoSuchMemberException, NoSuchHouseKeeperException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getAllMembers(int houseKeeperID) {
		// TODO Auto-generated method stub
		return null;
	}

}
