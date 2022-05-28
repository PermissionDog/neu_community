package com.github.permissiondog.community.view;

import javax.swing.JFrame;

import com.github.permissiondog.community.model.User;

public abstract class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected User user;
	
	public MainFrame(User user) {
		this.user = user;
	}


}
