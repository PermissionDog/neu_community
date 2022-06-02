package com.github.permissiondog.community.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.permissiondog.community.model.enumeration.Role;

/**
 * 入住人信息界面
 * 
 * @author PermissionDog
 * @see NewMemberFrame
 * @see ModifyMemberFrame
 *
 */
public abstract class MemberFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	protected JTextField textFieldName;
	protected JRadioButton rdbtnMale;
	protected JRadioButton rdbtnFemale;
	protected JTextField textFieldBirthday;
	protected JTextField textFieldPhone;
	protected JComboBox<Role> comboBoxRole;
	
	protected JButton btnConfirm;
	protected JButton btnCancel;

	public MemberFrame() {
		setBounds(100, 100, 419, 296);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 360, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 250, 0, 0};
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
		btnConfirm.setBounds(252, 192, 93, 23);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("取消");
		btnCancel.setBounds(10, 192, 93, 23);
		panel.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(80, 27, 54, 15);
		panel.add(lblNewLabel_2);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("1-10位英文字母汉字数字");
		textFieldName.setColumns(10);
		textFieldName.setBounds(152, 23, 139, 23);
		panel.add(textFieldName);
		
		JLabel lblNewLabel_2_1 = new JLabel("性别");
		lblNewLabel_2_1.setBounds(80, 64, 54, 15);
		panel.add(lblNewLabel_2_1);
		
		rdbtnMale = new JRadioButton("男");
		rdbtnMale.setSelected(true);
		rdbtnMale.setBounds(152, 60, 37, 23);
		panel.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("女");
		rdbtnFemale.setBounds(218, 60, 37, 23);
		panel.add(rdbtnFemale);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);
		
		JLabel lblNewLabel_2_2 = new JLabel("出生日期");
		lblNewLabel_2_2.setBounds(80, 99, 54, 15);
		panel.add(lblNewLabel_2_2);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setToolTipText("如 2022-5-26");
		textFieldBirthday.setColumns(10);
		textFieldBirthday.setBounds(152, 95, 139, 23);
		panel.add(textFieldBirthday);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("联系电话");
		lblNewLabel_2_2_1.setBounds(80, 132, 54, 15);
		panel.add(lblNewLabel_2_2_1);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setToolTipText("5-20位数字或加号");
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(152, 128, 139, 23);
		panel.add(textFieldPhone);
		

		btnCancel.addActionListener(e -> dispose());
	}

}
