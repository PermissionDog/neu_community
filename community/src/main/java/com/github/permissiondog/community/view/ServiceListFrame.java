package com.github.permissiondog.community.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import javax.swing.JComboBox;

public class ServiceListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<Member> comboBox;

	private List<Member> members;
	private User houseKeeper;

	public ServiceListFrame(int memberID) {
		houseKeeper = UserController.getInstance().getUser(memberID);

		setTitle(houseKeeper.getName() + "(" + houseKeeper.getUsername() + ") 所管理的老人列表");
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
		scrollPane.setBounds(10, 43, 425, 212);
		panel.add(scrollPane);

		JButton btnReturn = new JButton("返回");
		btnReturn.addActionListener(e -> dispose());
		btnReturn.setBounds(342, 265, 93, 23);
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

		JButton btnUnsetService = new JButton("取消服务");
		btnUnsetService.setBounds(10, 10, 93, 23);
		panel.add(btnUnsetService);

		JButton btnSetService = new JButton("设置服务");
		btnSetService.setBounds(342, 10, 93, 23);
		panel.add(btnSetService);

		comboBox = new JComboBox<Member>();
		comboBox.setBounds(227, 10, 105, 23);
		panel.add(comboBox);

		// 设置服务
		btnSetService.addActionListener(e -> {
			Member m = (Member) comboBox.getSelectedItem();
			if (m == null) {
				JOptionPane.showMessageDialog(this, "没有老人被选择", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			m = MemberController.getInstance().setService(houseKeeper.getId(), m.getId());
			if (m != null) {
				JOptionPane.showMessageDialog(this, "设置成功", "信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 取消服务
		btnUnsetService.addActionListener(e -> {

			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(ServiceListFrame.this, "请先选择要删除的入住人");
				return;
			}
			int result = JOptionPane.showConfirmDialog(ServiceListFrame.this, "是否要取消服务", "", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				return;
			}
			Arrays.stream(table.getSelectedRows()).map(i -> members.get(i).getId()).boxed()
					.collect(Collectors.toList()).forEach(id -> {
						if (MemberController.getInstance().unsetService(id) == null) {
							JOptionPane.showMessageDialog(ServiceListFrame.this, "取消失败", "错误",
									JOptionPane.ERROR_MESSAGE);
						}
					});
			JOptionPane.showMessageDialog(ServiceListFrame.this, "取消成功", "成功", JOptionPane.INFORMATION_MESSAGE);

		});

		// 表格刷新
		MemberController.getInstance().registerObeserver(this::flushTable);
		// 下拉框刷新
		MemberController.getInstance().registerObeserver(this::flushComboBox);

		flushComboBox();
		flushTable();
	}

	private void flushTable() {
		members = MemberController.getInstance().getAllMembers(houseKeeper.getId());

		Object[][] data = members.stream().parallel().map(member -> {
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

	private void flushComboBox() {
		List<Member> members = MemberController.getInstance().getAllMembers(-1);
		comboBox.setModel(new DefaultComboBoxModel<Member>(members.toArray(new Member[members.size()])));
	}
}
