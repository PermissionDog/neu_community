package com.github.permissiondog.community.view;


public class ModifySelfFrame extends ModifyUserFrame {

	private static final long serialVersionUID = 1L;

	public ModifySelfFrame(int id) {
		super(id);
		comboBoxRole.setEnabled(false);
	}

}
