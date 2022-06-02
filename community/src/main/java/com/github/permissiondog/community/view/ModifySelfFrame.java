package com.github.permissiondog.community.view;


/**
 * 修改个人信息界面
 * 
 * @author PermissionDog
 *
 */
public class ModifySelfFrame extends ModifyUserFrame {

	private static final long serialVersionUID = 1L;

	public ModifySelfFrame(int id) {
		super(id);
		comboBoxRole.setEnabled(false);
	}

}
