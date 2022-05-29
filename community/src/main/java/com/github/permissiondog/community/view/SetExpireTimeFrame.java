package com.github.permissiondog.community.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.BusController;
import com.github.permissiondog.community.model.Bus;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SetExpireTimeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Bus bus;
	private JTextField textFieldExpireTime;

	public SetExpireTimeFrame(int id) {
		setBounds(100, 100, 365, 255);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 300, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 150, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("截止时间");
		lblNewLabel.setBounds(46, 62, 54, 15);
		panel.add(lblNewLabel);
		
		textFieldExpireTime = new JTextField();
		textFieldExpireTime.setBounds(142, 59, 111, 21);
		panel.add(textFieldExpireTime);
		textFieldExpireTime.setToolTipText("如: 13:50, 15:03");
		textFieldExpireTime.setColumns(10);
		
		JButton btnConfirm = new JButton("确定");
		btnConfirm.setBounds(192, 131, 93, 23);
		panel.add(btnConfirm);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.setBounds(10, 131, 93, 23);
		panel.add(btnCancel);
		btnCancel.addActionListener(e -> dispose());
		
		bus = BusController.getInstance().getBus(id);
		
		setTitle("正在设置 " + bus.getName() + " 的截止时间");
		
		if (bus.getExpireTime() != null) {
			textFieldExpireTime.setText(bus.getExpireTime().format(Constants.TIME_FORMATTER));
		}
		
		btnConfirm.addActionListener(e -> {
			try {
				bus.setExpireTime(
					LocalTime.parse(textFieldExpireTime.getText(), Constants.TIME_FORMATTER));
			} catch (DateTimeParseException exception) {
				JOptionPane.showMessageDialog(this, "截止时间格式错误", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Bus newBus = BusController.getInstance().modifyBus(bus);
			if (newBus != null) {
				JOptionPane.showMessageDialog(this, "设置成功", "信息", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		
		
		
	}
}
