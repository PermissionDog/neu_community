package com.github.permissiondog.community.service;

import java.util.List;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchHouseKeeperException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.model.Member;

/**
 * 入住人 Service
 * 
 * @author PermissionDog
 *
 */
public interface MemberService extends BaseService {
	/**
	 * 创建新入住人
	 * 
	 * @param member	老人信息
	 * @return			成功返回老人
	 * @throws IllegalParameterException 参数错误
	 */
	public Member newMember(Member member) throws IllegalParameterException;
	
	/**
	 * 删除入住人
	 * 
	 * @param id	入住人ID
	 * @return		成功返回被删除的入住人, 未找到返回 null
	 */
	public Member deleteMember(int id);
	
	/**
	 * 获取入住人
	 * 
	 * @param id	入住人ID
	 * @return		成功返回入住人, 未找到返回 null
	 */
	public Member getMember(int id);
	
	/**
	 * 修改入住人
	 * 
	 * @param member 入住人
	 * @return	成功返回入住人, 失败返回 null
	 * @throws IllegalParameterException	参数错误
	 * @throws NoSuchMemberException		未找到入住人
	 */
	public Member modifyMember(Member member) throws IllegalParameterException, NoSuchMemberException;
	
	/**
	 * 设置服务
	 * 
	 * @param houseKeeperID	生活管家ID
	 * @param memberID		入住人ID
	 * @return	成功返回入住人, 失败返回 null
	 * @throws NoSuchMemberException		未找到入住人
	 * @throws NoSuchHouseKeeperException	未找到生活管家
	 * @throws IllegalParameterException	houseKeeperID 不是生活管家
	 */
	public Member setService(int houseKeeperID, int memberID) throws NoSuchMemberException, NoSuchHouseKeeperException, IllegalParameterException;
	
	/**
	 * 解除服务
	 * 
	 * @param id 入住人ID
	 * @return	成功返回入住人, 失败返回 null
	 */
	public Member unsetService(int id);
	
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
	 * @return	入住人, 若生活管家不存在返回空列表
	 */
	public List<Member> getAllMembers(int houseKeeperID);

}
