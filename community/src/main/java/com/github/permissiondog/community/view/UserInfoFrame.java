package com.github.permissiondog.community.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.permissiondog.community.model.enumeration.Role;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

public abstract class UserInfoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField textFieldUserName;
	protected JPasswordField passwordField;
	protected JTextField textFieldName;
	protected JRadioButton rdbtnMale;
	protected JRadioButton rdbtnFemale;
	protected JTextField textFieldBirthday;
	protected JTextField textFieldPhone;
	protected JComboBox<Role> comboBoxRole;
	
	protected JButton btnConfirm;
	protected JButton btnCancel;
	protected JLabel lblUserNameWarn;

	protected UserInfoFrame() {
		setBounds(100, 100, 419, 449);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 360, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 370, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		btnConfirm = new JButton("确定");
		btnConfirm.setBounds(252, 313, 93, 23);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(10, 313, 93, 23);
		panel.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(57, 31, 54, 15);
		panel.add(lblNewLabel);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setToolTipText("6-18位字母数字");
		textFieldUserName.setBounds(129, 27, 139, 23);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(57, 71, 54, 15);
		panel.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("8-18位字符，至少含有大写字母、小写字母、数字、特殊符号（~!@#$%^&*._）中的任意两种构成");
		passwordField.setBounds(129, 67, 139, 23);
		panel.add(passwordField);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("1-10位英文字母汉字数字");
		textFieldName.setColumns(10);
		textFieldName.setBounds(129, 106, 139, 23);
		panel.add(textFieldName);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(57, 110, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("性别");
		lblNewLabel_2_1.setBounds(57, 147, 54, 15);
		panel.add(lblNewLabel_2_1);
		
		rdbtnMale = new JRadioButton("男");
		rdbtnMale.setSelected(true);
		rdbtnMale.setBounds(129, 143, 37, 23);
		panel.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("女");
		rdbtnFemale.setBounds(195, 143, 37, 23);
		panel.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);
		
		JLabel lblNewLabel_2_2 = new JLabel("出生日期");
		lblNewLabel_2_2.setBounds(57, 182, 54, 15);
		panel.add(lblNewLabel_2_2);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setToolTipText("如 2022-5-26");
		textFieldBirthday.setColumns(10);
		textFieldBirthday.setBounds(129, 178, 139, 23);
		panel.add(textFieldBirthday);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("联系电话");
		lblNewLabel_2_2_1.setBounds(57, 215, 54, 15);
		panel.add(lblNewLabel_2_2_1);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setToolTipText("5-20位数字或加号");
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(129, 211, 139, 23);
		panel.add(textFieldPhone);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("权限");
		lblNewLabel_2_2_1_1.setBounds(57, 252, 54, 15);
		panel.add(lblNewLabel_2_2_1_1);
		
		comboBoxRole = new JComboBox<Role>();
		comboBoxRole.setModel(new DefaultComboBoxModel<Role>(Role.values()));
		comboBoxRole.setBounds(129, 248, 139, 23);
		panel.add(comboBoxRole);
		
		lblUserNameWarn = new JLabel("");
		lblUserNameWarn.setForeground(Color.RED);
		lblUserNameWarn.setBounds(278, 31, 93, 15);
		panel.add(lblUserNameWarn);
	}
}
