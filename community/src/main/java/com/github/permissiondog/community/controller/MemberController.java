package com.github.permissiondog.community.controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.exception.NoSuchHouseKeeperException;
import com.github.permissiondog.community.exception.NoSuchMemberException;
import com.github.permissiondog.community.exception.NoSuchUserException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.service.MemberService;
import com.github.permissiondog.community.service.impl.MemberServiceImpl;
import com.github.permissiondog.community.view.MemberListFrame;
import com.github.permissiondog.community.view.ModifyMemberFrame;
import com.github.permissiondog.community.view.NewMemberFrame;
import com.github.permissiondog.community.view.ServiceListFrame;

/**
 * 入住人 Controller
 * 
 * @author PermissionDog
 *
 */
public class MemberController {
	private static MemberController memberController;

	public static MemberController getInstance() {
		if (memberController == null) {
			memberController = new MemberController();
		}
		return memberController;
	}

	private MemberController() {
	}

	/**
	 * 创建新入住人
	 * 
	 * @param member 老人信息
	 * @return 创建成功返回老人
	 */
	public Member newMember(Member member) {
		MemberService memberService = MemberServiceImpl.getInstance();
		Member m;
		try {
			m = memberService.newMember(member);
		} catch (IllegalParameterException e) {
			JOptionPane.showInternalMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JOptionPane.showMessageDialog(null, "创建成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		return m;
	}

	/**
	 * 获取全部入住人
	 * 
	 * @return 所有入住人
	 */
	public List<Member> getAllMembers() {
		MemberService memberService = MemberServiceImpl.getInstance();
		return memberService.getAllMembers();
	}

	/**
	 * 获取生活管家所管理的全部入住人
	 * 
	 * @param id 生活管家ID, 若为 -1, 返回未设置服务的老人列表
	 * @return 入住人
	 */
	public List<Member> getAllMembers(int id) {
		MemberService memberService = MemberServiceImpl.getInstance();
		if (id == -1) {
			return Arrays.asList(memberService.getAllMembers().stream().filter(member -> member.getHouseKeeperID() == -1).toArray(Member[]::new));
		}
		return memberService.getAllMembers(id);
	}

	/**
	 * 删除入住人
	 * 
	 * @param id 入住人ID
	 * @return 成功返回被删除的入住人, 失败返回 null
	 */
	public Member deleteMember(int id) {
		MemberService memberService = MemberServiceImpl.getInstance();
		return memberService.deleteMember(id);
	}

	/**
	 * 获取入住人
	 * 
	 * @param id 入住人ID
	 * @return 成功返回入住人, 失败返回 null
	 */
	public Member getMember(int id) {
		MemberService memberService = MemberServiceImpl.getInstance();
		return memberService.getMember(id);
	}

	/**
	 * 修改入住人
	 * 
	 * @param m 修改的入住人
	 * @return 成功返回入住人, 失败返回 null
	 */
	public Member modifyMember(Member m) {
		MemberService memberService = MemberServiceImpl.getInstance();
		try {
			m = memberService.modifyMember(m);
		} catch (NoSuchUserException e) {
			JOptionPane.showMessageDialog(null, "入住人不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		return m;
	}

	/**
	 * 设置服务
	 * 
	 * @param houseKeeperID 生活管家ID
	 * @param memberID      入住人ID
	 * @return 设置成功返回入住人, 失败返回 null
	 */
	public Member setService(int houseKeeperID, int memberID) {
		MemberService memberService = MemberServiceImpl.getInstance();
		Member m;
		try {
			m = memberService.setService(houseKeeperID, memberID);
		} catch (NoSuchMemberException e) {
			JOptionPane.showMessageDialog(null, "入住人不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (NoSuchHouseKeeperException e) {
			JOptionPane.showMessageDialog(null, "生活管家不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return m;
	}

	/**
	 * 解除服务
	 * 
	 * @param id 入住人ID
	 * @return 解除成功返回入住人, 失败返回 null
	 */
	public Member unsetService(int id) {
		MemberService memberService = MemberServiceImpl.getInstance();
		Member m;
		try {
			m = memberService.unsetService(id);
		} catch (NoSuchMemberException e) {
			JOptionPane.showMessageDialog(null, "入住人不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (NoSuchHouseKeeperException e) {
			JOptionPane.showMessageDialog(null, "生活管家不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (IllegalParameterException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return m;
	}

	/**
	 * 显示新建入住人窗口
	 */
	public void showNewMemberFrame() {
		SwingUtilities.invokeLater(() -> {
			new NewMemberFrame().setVisible(true);
		});
	}

	/**
	 * 显示老人列表
	 */
	public void showMemberListFrame() {
		SwingUtilities.invokeLater(() -> {
			new MemberListFrame().setVisible(true);
		});
	}

	/**
	 * 显示老人修改窗口
	 * 
	 * @param id 要修改的老人ID
	 */
	public void showModifyMemberFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new ModifyMemberFrame(id).setVisible(true);
		});
	}

	/**
	 * 显示服务列表
	 * 
	 * @param id 生活管家ID
	 */
	public void showServiceListFrame(int id) {
		SwingUtilities.invokeLater(() -> {
			new ServiceListFrame(id).setVisible(true);
		});
	}

	/**
	 * 注册观察者
	 * 
	 * @param o 观察者
	 */
	public void registerObeserver(Observer o) {
		MemberService memberService = MemberServiceImpl.getInstance();
		memberService.registerObserver(o);
	}

}
