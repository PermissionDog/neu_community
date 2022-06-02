package com.github.permissiondog.community.model.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.Table;
import com.github.permissiondog.community.model.dao.MemberDao;
import com.github.permissiondog.community.util.FileUtil;
import com.github.permissiondog.community.util.GsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 入住人 Dao 实现
 * 
 * @author PermissionDog
 *
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {
	private static MemberDao memberDao;
	
	public static MemberDao getInstance() {
		if (memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}
	
	private MemberDaoImpl() {
		load();
	}
	
	private Map<Integer, Member> members;
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
	public Map<Integer, Member> getData() {
		return members;
	}
	
	@Override
	public void load() {
		members = new HashMap<>();
		String json = FileUtil.readFile(Constants.MEMBER_TABLE_NAME);
		Table<Member> table = GsonUtil.gson.fromJson(json, new TypeToken<Table<Member>>() {
		}.getType());
		idCount = table.getIdCount();
		for (Member m : table.getData()) {
			members.put(m.getId(), m);
		}
	}

	@Override
	public void save() {
		Table<Member> table = new Table<>();
		table.setIdCount(idCount);
		table.setData(members.values());
		String json = GsonUtil.gson.toJson(table);
		FileUtil.writeFile(Constants.MEMBER_TABLE_NAME, json);
	}
	
}
