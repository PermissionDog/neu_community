package com.github.permissiondog.community.controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.exception.IllegalParameterException;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.enumeration.Gender;
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
	 * @param name		老人姓名
	 * @param gender	性别
	 * @param birthday	出生日期
	 * @param phone		联系电话
	 * @return			创建成功返回老人
	 */
	public Member newMember(String name, Gender gender, LocalDate birthday, String phone) {
		MemberService memberService = MemberServiceImpl.getInstance();
		Member m;
		try {
			m = memberService.newMember(name, gender, birthday, phone);
		} catch (IllegalParameterException e) {
			JOptionPane.showInternalMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		JOptionPane.showMessageDialog(null, "创建成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		return m;
	}
	
}
