package com.github.permissiondog.community.service;

import java.time.LocalDate;
import java.util.List;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchHouseKeeperException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.enumeration.Gender;

public interface MemberService {
	/**
	 * 创建新入住人
	 * 
	 * @param name		老人姓名
	 * @param gender	性别
	 * @param birthday	出生日期
	 * @param phone		联系电话
	 * @return			成功返回老人
	 * @throws IllegalParameterException 参数错误
	 */
	public Member newMember(String name, Gender gender, LocalDate birthday, String phone) throws IllegalParameterException;
	
	/**
	 * 删除入住人
	 * 
	 * @param id	入住人ID
	 * @return		成功返回被删除的入住人, 失败返回 null
	 * @throws NoSuchMemberException 未找到入住人
	 */
	public Member deleteMember(int id) throws NoSuchMemberException;
	
	/**
	 * 设置服务
	 * 
	 * @param houseKeeperID	生活管家ID
	 * @param memberID		入住人ID
	 * @return	成功返回入住人, 失败返回 null
	 * @throws NoSuchMemberException		未找到入住人
	 * @throws NoSuchHouseKeeperException	未找到生活管家
	 */
	public Member setService(int houseKeeperID, int memberID) throws NoSuchMemberException, NoSuchHouseKeeperException;
	
	/**
	 * 获取所有入住人信息
	 * 
	 * @return	入住人
	 */
	public List<Member> getAllMembers();
	
	/**
	 * 获取被某个生活管家服务的入住人列表
	 * 
	 * @param houseKeeperID	生活管家ID
	 * @return	入住人
	 */
	public List<Member> getAllMembers(int houseKeeperID);
}
