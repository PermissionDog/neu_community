package com.github.permissiondog.community.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.User;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 300, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 130, 50, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblUsername = new JLabel("\u7528\u6237\u540D");
		lblUsername.setFont(new Font("宋体", Font.PLAIN, 12));
		lblUsername.setBounds(10, 10, 97, 42);
		panel.add(lblUsername);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("宋体", Font.PLAIN, 12));
		userNameTextField.setBounds(117, 20, 173, 22);
		panel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801");
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 12));
		lblPassword.setBounds(10, 57, 97, 42);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 12));
		passwordField.setBounds(117, 68, 173, 22);
		panel.add(passwordField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(41, 0, 203, 35);
		panel_2.add(button);
		
		button.addActionListener(e -> login());
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
	}
	private void login() {
		String username = userNameTextField.getText();
		String password = new String(passwordField.getPassword());
		User u = UserController.getInstance().login(username, password);
		if (u != null) {
			dispose();
			UserController.getInstance().showMainFrame(u);
		}
	}
}
