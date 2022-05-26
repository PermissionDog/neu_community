package com.github.permissiondog.community.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;

public class RegisterFrame extends UserInfoFrame {

	private static final long serialVersionUID = 1L;

	public RegisterFrame() {
		super();
		setTitle("注册");
		super.btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserController.getInstance().register(
						textFieldUserName.getText(),
						new String(passwordField.getPassword()),
						textFieldName.getText(),
						rdbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE,
						LocalDate.parse(textFieldBirthday.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						textFieldPhone.getText(),
						(Role) comboBoxRole.getSelectedItem()
						);
			}
		});
	}

}
