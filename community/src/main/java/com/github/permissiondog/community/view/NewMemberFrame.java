package com.github.permissiondog.community.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.enumeration.Gender;

public class NewMemberFrame extends MemberFrame {

	private static final long serialVersionUID = 1L;

	public NewMemberFrame() {
		super();
		setTitle("新增入住人");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
			}
		});
	}

}
