package com.github.permissiondog.community.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.model.Member;

import javax.swing.JButton;

public class MemberListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private List<Member> members;

	public MemberListFrame() {
		setTitle("老人管理");
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

		JButton btnNewMember = new JButton("添加老人");
		btnNewMember.setBounds(10, 10, 93, 23);
		panel.add(btnNewMember);

		JButton btnDeleteMember = new JButton("删除老人");
		btnDeleteMember.setBounds(174, 10, 93, 23);
		panel.add(btnDeleteMember);

		JButton btnModifyMember = new JButton("修改老人");
		btnModifyMember.setBounds(342, 10, 93, 23);
		panel.add(btnModifyMember);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.addActionListener(e -> dispose());
		btnReturn.setBounds(342, 265, 93, 23);
		panel.add(btnReturn);

		//新增入住人
		btnNewMember.addActionListener(e -> MemberController.getInstance().showNewMemberFrame());
		//删除入住人
		btnDeleteMember.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(MemberListFrame.this, "请先选择要删除的入住人");
				return;
			}
			int result = JOptionPane.showConfirmDialog(MemberListFrame.this, "是否要删除", "", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				return;
			}
			int[] ids = Arrays.stream(table.getSelectedRows()).map(i -> members.get(i).getId()).toArray();
			Arrays.stream(ids).forEach(id -> {
				if (MemberController.getInstance().deleteMember(id) == null) {
					JOptionPane.showMessageDialog(MemberListFrame.this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			});
			JOptionPane.showMessageDialog(MemberListFrame.this, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		});
		//修改入住人
		btnModifyMember.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(MemberListFrame.this, "请先选择要修改的用户");
				return;
			}
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(MemberListFrame.this, "最多选择一个要修改的用户");
				return;
			}
			int id = members.get(table.getSelectedRow()).getId();
			MemberController.getInstance().showModifyMemberFrame(id);
		});

		MemberController.getInstance().registerObeserver(this::flushTable);

		flushTable();
	}

	private void flushTable() {
		members = MemberController.getInstance().getAllMembers();
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
}
