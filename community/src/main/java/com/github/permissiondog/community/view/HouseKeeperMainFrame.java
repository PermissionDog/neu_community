package com.github.permissiondog.community.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.Member;
import com.github.permissiondog.community.model.User;

public class HouseKeeperMainFrame extends MainFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	
	private List<Member> members;

	public HouseKeeperMainFrame(User user) {
		super(user);
		
		setTitle("生活管家: " + user.getName());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 421);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 450, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 300, 0, 0 };
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 425, 245);
		panel.add(scrollPane);

		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(78, 265, 110, 23);
		panel.add(btnReturn);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null },
						{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "ID", "\u59D3\u540D", "\u6027\u522B", "\u51FA\u751F\u65E5\u671F", "\u7535\u8BDD" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnModifySelf = new JButton("修改个人信息");
		btnModifySelf.setBounds(273, 265, 110, 23);
		panel.add(btnModifySelf);
		
		//修改个人信息
		btnModifySelf.addActionListener(e -> UserController.getInstance().showModifySelfFrame(user.getId()));
		
		//返回
		btnReturn.addActionListener(e -> {
			UserController.getInstance().showLoginFrame();
			dispose();
		});
		
		//表格刷新
		MemberController.getInstance().registerObeserver(this::flushTable);
		//名称刷新
		UserController.getInstance().registerObserver(this::flushTitle);
		
		flushTable();
	}

	private void flushTable() {
		members = MemberController.getInstance().getAllMembers(user.getId());
		Object[][] data = members.stream().map(member -> {
			Object[] obj = new Object[5];
			obj[0] = member.getId();
			obj[1] = member.getName();
			obj[2] = member.getGender();
			obj[3] = member.getBirthday().format(Constants.DATE_FORMATTER);
			obj[4] = member.getPhone();
			return obj;
		}).toArray(Object[][]::new);
		

		table.setModel(new DefaultTableModel(data,
				new String[] { "ID", "\u59D3\u540D", "\u6027\u522B", "\u51FA\u751F\u65E5\u671F", "\u7535\u8BDD" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}
	private void flushTitle() {
		user = UserController.getInstance().getUser(user.getId());
		setTitle("生活管家: " + user.getName());
	}
}
