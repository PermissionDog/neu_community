package com.github.permissiondog.community.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.User;
import com.github.permissiondog.community.model.enumeration.Role;

import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AdminMainFrame extends MainFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;

	private List<User> users;
	private JTextField textFieldSearch;

	public AdminMainFrame(User user) {
		super(user);

		setTitle("管理员: " + user.getName());

		setBounds(100, 100, 705, 422);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 600, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 40, 80, 220, 35, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);

		JButton btnNewUser = new JButton("新增用户");
		btnNewUser.setBounds(10, 10, 93, 23);
		panel.add(btnNewUser);

		JButton btnDeleteUser = new JButton("删除用户");
		btnDeleteUser.setBounds(10, 42, 93, 23);
		panel.add(btnDeleteUser);

		textFieldSearch = new JTextField();
		textFieldSearch.setToolTipText("输入关键词搜索");
		textFieldSearch.setBounds(250, 42, 206, 23);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JButton btnShowService = new JButton("查看服务");
		btnShowService.setBounds(492, 42, 93, 23);
		panel.add(btnShowService);

		JButton btnShowMember = new JButton("查看老人");
		btnShowMember.setBounds(492, 10, 93, 23);
		panel.add(btnShowMember);

		JButton btnModifyUser = new JButton("修改用户");
		btnModifyUser.setBounds(113, 42, 93, 23);
		panel.add(btnModifyUser);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, "", null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, },
				new String[] { "ID", "\u7528\u6237", "\u59D3\u540D", "\u6027\u522B", "\u51FA\u751F\u65E5\u671F",
						"\u7535\u8BDD", "\u6743\u9650" }));
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);

		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(490, 0, 93, 23);
		panel_1.add(btnReturn);

		// 搜索框
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				flushTable(textFieldSearch.getText());
			}
		});

		// 新增用户
		btnNewUser.addActionListener(e -> UserController.getInstance().showRegisterFrame());
		// 删除用户
		btnDeleteUser.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "请先选择要删除的用户");
				return;
			}
			int result = JOptionPane.showConfirmDialog(AdminMainFrame.this, "是否要删除", "", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				return;
			}

			Arrays.stream(table.getSelectedRows()).map(i -> users.get(i).getId()).boxed()
					.collect(Collectors.toList()).forEach(id -> {
						if (UserController.getInstance().deleteUser(id) == null) {
							JOptionPane.showMessageDialog(AdminMainFrame.this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
						}
					});

			JOptionPane.showMessageDialog(AdminMainFrame.this, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		});
		// 修改用户
		btnModifyUser.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "请先选择要修改的用户");
				return;
			}
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "最多选择一个要修改的用户");
				return;
			}
			int id = users.get(table.getSelectedRow()).getId();
			UserController.getInstance().showModifyUserFrame(id);
		});

		// 查看老人列表
		btnShowMember.addActionListener(e -> MemberController.getInstance().showMemberListFrame());

		// 查看服务
		btnShowService.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "请先选择要查看服务的生活管家");
				return;
			}
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "最多选择一个要查看的生活管家");
				return;
			}
			User u = users.get(table.getSelectedRow());
			if (!u.getRole().equals(Role.HOUSEKEEPER)) {
				JOptionPane.showMessageDialog(AdminMainFrame.this, "请选择生活管家");
				return;
			}
			MemberController.getInstance().showServiceListFrame(u.getId());
		});

		// 返回
		btnReturn.addActionListener(e -> {
			UserController.getInstance().showLoginFrame();
			dispose();
		});

		// 注册观察者
		UserController.getInstance().registerObserver(this::flushTable);
		UserController.getInstance().registerObserver(this::flushTitle);

		// 刷新表格
		flushTable();
	}

	private void flushTable() {
		users = UserController.getInstance().getAllUsers();
		Object[][] data = users.stream().parallel().map(user -> {
			Object[] obj = new Object[7];
			obj[0] = user.getId();
			obj[1] = user.getUsername();
			obj[2] = user.getName();
			obj[3] = user.getGender();
			obj[4] = user.getBirthday().format(Constants.DATE_FORMATTER);
			obj[5] = user.getPhone();
			obj[6] = user.getRole();
			return obj;
		}).toArray(Object[][]::new);

		table.setModel(new DefaultTableModel(data, new String[] { "ID", "\u7528\u6237", "\u59D3\u540D", "\u6027\u522B",
				"\u51FA\u751F\u65E5\u671F", "\u7535\u8BDD", "\u6743\u9650" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	public void flushTable(String keyword) {
		users = UserController.getInstance().getAllUsers();
		Object[][] data = users.stream().parallel().map(user -> {
			Object[] obj = new Object[7];
			obj[0] = user.getId();
			obj[1] = user.getUsername();
			obj[2] = user.getName();
			obj[3] = user.getGender();
			obj[4] = user.getBirthday().format(Constants.DATE_FORMATTER);
			obj[5] = user.getPhone();
			obj[6] = user.getRole();
			return obj;
		}).filter(user -> Arrays.stream(user).map(Object::toString).anyMatch(t -> t.contains(keyword)))
				.toArray(Object[][]::new);

		table.setModel(new DefaultTableModel(data, new String[] { "ID", "\u7528\u6237", "\u59D3\u540D", "\u6027\u522B",
				"\u51FA\u751F\u65E5\u671F", "\u7535\u8BDD", "\u6743\u9650" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}

	private void flushTitle() {
		user = UserController.getInstance().getUser(user.getId());
		setTitle("管理员: " + user.getName());
	}
}
