package com.github.permissiondog.community.controller;


import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.dao.Observer;
import com.github.permissiondog.community.service.MemberService;
import com.github.permissiondog.community.service.impl.MemberServiceImpl;
import com.github.permissiondog.community.view.MemberListFrame;
import com.github.permissiondog.community.view.NewMemberFrame;

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
	 * @param member	老人信息
	 * @return			创建成功返回老人
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
	 * @param id 生活管家ID
	 * @return	入住人
	 */
	public List<Member> getAllMembers(int id) {
		MemberService memberService = MemberServiceImpl.getInstance();
		return memberService.getAllMembers(id);
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
	 * 注册观察者
	 * @param o 观察者
	 */
	public void registerObeserver(Observer o) {
		MemberService memberService = MemberServiceImpl.getInstance();
		memberService.registerObserver(o);
	}
	
}
