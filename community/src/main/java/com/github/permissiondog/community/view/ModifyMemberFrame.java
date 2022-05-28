package com.github.permissiondog.community.view;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.enumeration.Gender;

public class ModifyMemberFrame extends MemberFrame {

	private static final long serialVersionUID = 1L;
	
	private Member member;

	public ModifyMemberFrame(int id) {
		super();
		
		member = MemberController.getInstance().getMember(id);
		textFieldName.setText(member.getName());
		if (member.getGender().equals(Gender.MALE)) {
			rdbtnMale.setSelected(true);
		} else {
			rdbtnFemale.setSelected(true);
		}
		textFieldPhone.setText(member.getPhone());
		textFieldBirthday.setText(member.getBirthday().format(Constants.DATE_FORMATTER));
		
		setTitle("修改老人: " + member.getName());
		
		btnConfirm.addActionListener(e -> {
			LocalDate birthday;
			try {
				birthday = LocalDate.parse(textFieldBirthday.getText(), Constants.DATE_FORMATTER);
			} catch (DateTimeParseException exception) {
				JOptionPane.showMessageDialog(ModifyMemberFrame.this, "出生日期格式错误", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Member m = new Member(); 
			m.setId(member.getId());
			m.setName(textFieldName.getText());
			m.setGender(rdbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE);
			m.setBirthday(birthday);
			m.setPhone(textFieldPhone.getText());
			
			m = MemberController.getInstance().modifyMember(m);
			if (m != null) {
				dispose();
			}
		});
	}

}
