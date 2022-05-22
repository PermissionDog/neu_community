package com.github.permissiondog.community.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.github.permissiondog.community.model.User;

public class AdminMainFrame extends MainFrame {

	
	public AdminMainFrame(User user) {
		super(user);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
