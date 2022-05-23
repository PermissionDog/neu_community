package com.github.permissiondog.community.view;


import javax.swing.JFrame;

import com.github.permissiondog.community.model.User;

public class HouseKeeperMainFrame extends MainFrame {

	private static final long serialVersionUID = 1L;

	public HouseKeeperMainFrame(User user) {
		super(user);
		
		setTitle("生活管家: " + user.getName());
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
