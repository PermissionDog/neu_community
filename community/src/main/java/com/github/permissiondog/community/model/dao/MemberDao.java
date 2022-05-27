package com.github.permissiondog.community.model.dao;

import java.util.List;

import com.github.permissiondog.community.model.Member;

public interface MemberDao {
	/**
	 * 插入入住人
	 * 
	 * @param member 入住人
	 * @return	插入成功返回入住人, 失败返回 null
	 */
	public Member insertMember(Member member);
	/**
	 * 删除入住人
	 * 
	 * @param id	入住人ID
	 * @return		删除成功返回被删除的入住人, 失败返回 null
	 */
	public Member deleteMember(int id);
	/**
	 * 修改入住人
	 * 
	 * @param member	入住人ID
	 * @return	修改成功返回被更新的入住人, 失败返回 null
	 */
	public Member updateMember(Member member);
	/**
	 * 获取所有入住人
	 * 
	 * @return	所有入住人
	 */
	public List<Member> getAllMembers();
}
