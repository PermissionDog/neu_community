package com.github.permissiondog.community.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.Table;
import com.github.permissiondog.community.model.dao.MemberDao;
import com.github.permissiondog.community.util.FileUtil;
import com.github.permissiondog.community.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

public class MemberDaoImpl implements MemberDao {
	private static MemberDao memberDao;
	
	public static MemberDao getInstance() {
		if (memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}
	
	private MemberDaoImpl() {
		loadMembers();
	}
	
	private Map<Integer, Member> members;
	private int idCount;
	
	private void loadMembers() {
		members = new HashMap<>();
		String memberJson = FileUtil.readFile(Constants.MEMBER_TABLE_NAME);
		Table<Member> memberTable = GsonUtil.gson.fromJson(memberJson, new TypeToken<Table<Member>>() {
		}.getType());
		idCount = memberTable.getIdCount();
		for (Member m : memberTable.getData()) {
			members.put(m.getId(), m);
		}
	}
	
	private void saveMembers() {
		Table<Member> memberTable = new Table<>();
		memberTable.setIdCount(idCount);
		memberTable.setData(members.values());
		String memberJson = GsonUtil.gson.toJson(memberTable);
		FileUtil.writeFile(Constants.MEMBER_TABLE_NAME, memberJson);
	}
	

	@Override
	public Member insertMember(Member member) {
		member.setId(++idCount);
		members.put(member.getId(), member);
		saveMembers();
		
		return member;
	}

	@Override
	public Member deleteMember(int id) {
		Member m = members.remove(id);
		if (m != null)
			saveMembers();
		
		return m;
	}

	@Override
	public Member updateMember(Member member) {
		if (!members.containsKey(member.getId())) {
			return null;
		}
		members.put(member.getId(), member);
		saveMembers();
		return member;
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

}
