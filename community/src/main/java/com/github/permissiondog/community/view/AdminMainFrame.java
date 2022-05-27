package com.github.permissiondog.community.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.User;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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
		gridBagLayout.rowHeights = new int[] { 40, 80, 220, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
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
		textFieldSearch.setBounds(160, 42, 201, 23);
		panel.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnNewMember = new JButton("添加老人");
		btnNewMember.setBounds(389, 10, 93, 23);
		panel.add(btnNewMember);
		
		JButton btnSetService = new JButton("设置服务");
		btnSetService.setBounds(492, 42, 93, 23);
		panel.add(btnSetService);
		
		JButton btnShowService = new JButton("查看服务");
		btnShowService.setBounds(389, 42, 93, 23);
		panel.add(btnShowService);
		
		JButton btnModifyMember = new JButton("修改老人");
		btnModifyMember.setBounds(492, 10, 93, 23);
		panel.add(btnModifyMember);

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
		
		//新增用户
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserController.getInstance().showRegisterFrame(() -> {
					flushTable();
				});
			}
		});
		//删除用户
		btnDeleteUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1) {
					JOptionPane.showMessageDialog(AdminMainFrame.this, "请先选择要删除的用户");
					return;
				}
				int result = JOptionPane.showConfirmDialog(AdminMainFrame.this, "是否要删除", "", JOptionPane.YES_NO_OPTION);
				if (result != JOptionPane.YES_OPTION) {
					return;
				}
				Arrays.stream(table.getSelectedRows()).forEach((i) -> {
					int id = users.get(i).getId();
					if (UserController.getInstance().deleteUser(id) == null) {
						JOptionPane.showMessageDialog(AdminMainFrame.this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				});
				flushTable();
				JOptionPane.showMessageDialog(AdminMainFrame.this, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//刷新表格
		flushTable();
	}

	public void flushTable() {
		users = UserController.getInstance().getAllUsers();
		int size = users.size();
		Object[][] data = new Object[size][];
		for (int i = 0; i < size; i++) {
			User user = users.get(i);
			Object[] obj = new Object[7];
			obj[0] = user.getId();
			obj[1] = user.getUsername();
			obj[2] = user.getName();
			obj[3] = user.getGender();
			obj[4] = user.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			obj[5] = user.getPhone();
			obj[6] = user.getRole();
			data[i] = obj;
		}

		table.setModel(new DefaultTableModel(data, new String[] { "ID", "\u7528\u6237", "\u59D3\u540D", "\u6027\u522B",
				"\u51FA\u751F\u65E5\u671F", "\u7535\u8BDD", "\u6743\u9650" }) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}
}
