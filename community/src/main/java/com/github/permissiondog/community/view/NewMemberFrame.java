package com.github.permissiondog.community.view;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.enumeration.Gender;

/**
 * 创建新入住人界面
 * 
 * @author PermissionDog
 *
 */
public class NewMemberFrame extends MemberFrame {

	private static final long serialVersionUID = 1L;

	public NewMemberFrame() {
		super();
		setTitle("新增入住人");
		btnConfirm.addActionListener(e -> {
				LocalDate birthday;
				try {
					birthday = LocalDate.parse(textFieldBirthday.getText(), Constants.DATE_FORMATTER);
				} catch (DateTimeParseException exception) {
					JOptionPane.showMessageDialog(NewMemberFrame.this, "出生日期格式错误", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Member m = new Member();
				m.setName(textFieldName.getText());
				m.setGender(rdbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE);
				m.setBirthday(birthday);
				m.setPhone(textFieldPhone.getText());
				
				m = MemberController.getInstance().newMember(m);
				if (m != null) {
					dispose();
				}
			});;
	}

}
