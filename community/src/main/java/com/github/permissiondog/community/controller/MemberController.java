package com.github.permissiondog.community.controller;


import javax.swing.JOptionPane;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.service.MemberService;
import com.github.permissiondog.community.service.impl.MemberServiceImpl;

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
	
}
