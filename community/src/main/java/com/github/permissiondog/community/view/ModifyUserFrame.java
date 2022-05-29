package com.github.permissiondog.community.view;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Role;

public class ModifyUserFrame extends UserInfoFrame {

	private static final long serialVersionUID = 1L;
	
	private User user;

	public ModifyUserFrame(int id) {
		super();
		textFieldUserName.setEditable(false);
		comboBoxRole.setEditable(false);
		
		user = UserController.getInstance().getUser(id);
		textFieldUserName.setText(user.getUsername());
		textFieldName.setText(user.getName());
		if (user.getGender().equals(Gender.MALE)) {
			rdbtnMale.setSelected(true);
		} else {
			rdbtnFemale.setSelected(true);
		}
		textFieldBirthday.setText(user.getBirthday().format(Constants.DATE_FORMATTER));
		textFieldPhone.setText(user.getPhone());
		comboBoxRole.setSelectedItem(user.getRole());
		
		setTitle("修改用户: " + user.getName());
		
		btnConfirm.addActionListener(e -> {
			{
				LocalDate birthday;
				try {
					birthday = LocalDate.parse(textFieldBirthday.getText(), Constants.DATE_FORMATTER);
				} catch (DateTimeParseException exception) {
					JOptionPane.showMessageDialog(ModifyUserFrame.this, "出生日期格式错误", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				User u = new User(); 
				u.setId(user.getId());
				u.setUsername(textFieldUserName.getText());
				if (passwordField.getPassword().length == 0) {
					u.setPassword(null);
				} else {
					u.setPassword(new String(passwordField.getPassword()));
				}
				u.setName(textFieldName.getText());
				u.setGender(rdbtnMale.isSelected() ? Gender.MALE : Gender.FEMALE);
				u.setBirthday(birthday);
				u.setPhone(textFieldPhone.getText());
				u.setRole((Role) comboBoxRole.getSelectedItem());
				
				User newUser = UserController.getInstance().modifyUser(u);
				if (newUser != null) {
					ModifyUserFrame.this.dispose();
				}
			}
		});
	}

}
