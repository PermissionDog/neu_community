package com.github.permissiondog.community.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Callback;
import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;
import com.github.permissiondog.community.model.User;

public class RegisterFrame extends UserInfoFrame {

	private static final long serialVersionUID = 1L;

	public RegisterFrame(Callback cb) {
		super();
		setTitle("注册");
		super.btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate birthday;
				try {
					birthday = LocalDate.parse(textFieldBirthday.getText(), DateTimeFormatter.ofPattern("yyyy-M-dd"));
				} catch (DateTimeParseException exception) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "出生日期格式错误", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				User u = UserController.getInstance().register(
						textFieldUserName.getText(),
						new String(passwordField.getPassword()),
						textFieldName.getText(),
						rdbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE,
						birthday,
						textFieldPhone.getText(),
						(Role) comboBoxRole.getSelectedItem()
						);
				if (u != null) {
					RegisterFrame.this.dispose();
					cb.callback();
				}
				
			}
		});
		super.btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterFrame.this.dispose();
			}
		});
		super.textFieldUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				User u = UserController.getInstance().getUser(textFieldUserName.getText());
				if (u != null) {
					lblUserNameWarn.setText("用户名已存在");
				} else {
					lblUserNameWarn.setText("");
				}
			}
		});
	}

}
