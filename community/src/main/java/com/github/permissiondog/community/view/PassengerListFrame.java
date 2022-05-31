package com.github.permissiondog.community.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.BusController;
import com.github.permissiondog.community.controller.MemberController;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.Member;

public class PassengerListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private Bus bus;
	private JTable table;
	private JComboBox<Member> comboBox;

	private List<Member> members;

	public PassengerListFrame(int id) {
		setBounds(100, 100, 570, 448);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 450, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 340, 0, 0 };
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

		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(342, 302, 93, 23);
		panel.add(btnReturn);
		btnReturn.addActionListener(e -> dispose());

		bus = BusController.getInstance().getBus(id);

		setTitle("班车 " + bus.getName() + " 乘客列表");

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 425, 249);
		panel.add(scrollPane);

		scrollPane.setViewportView(table);

		JButton btnRemoveReservation = new JButton("预约确认");
		btnRemoveReservation.setBounds(10, 10, 93, 23);
		panel.add(btnRemoveReservation);

		JButton btnReserve = new JButton("预约班车");
		btnReserve.setBounds(342, 10, 93, 23);
		panel.add(btnReserve);

		comboBox = new JComboBox<Member>();
		comboBox.setBounds(227, 10, 105, 23);
		panel.add(comboBox);

		// 登记
		btnReserve.addActionListener(e -> {

			Member m = (Member) comboBox.getSelectedItem();
			if (m == null) {
				JOptionPane.showMessageDialog(this, "没有人被选择", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Bus newBus = BusController.getInstance().reserveBus(bus.getId(), m.getId());
			if (newBus != null) {
				JOptionPane.showMessageDialog(this, "设置成功", "信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 预约确认
		btnRemoveReservation.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(this, "请先选择要确认的乘客");
				return;
			}
			int result = JOptionPane.showConfirmDialog(this, "是否要进行确认", "", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				return;
			}
			Arrays.stream(table.getSelectedRows()).map(i -> members.get(i).getId()).mapToObj(Integer::valueOf)
					.collect(Collectors.toList()).forEach(memberID -> {
						if (BusController.getInstance().removeReservation(bus.getId(), memberID) == null) {
							JOptionPane.showMessageDialog(this, "确认失败", "错误", JOptionPane.ERROR_MESSAGE);
						}
					});
			JOptionPane.showMessageDialog(this, "确认成功", "成功", JOptionPane.INFORMATION_MESSAGE);

		});

		// 表格刷新
		MemberController.getInstance().registerObeserver(this::flushTable);
		BusController.getInstance().registerObeserver(this::flushTable);
		// 下拉框刷新
		MemberController.getInstance().registerObeserver(this::flushComboBox);
		BusController.getInstance().registerObeserver(this::flushComboBox);

		BusController.getInstance().registerObeserver(() -> {
			bus = BusController.getInstance().getBus(bus.getId());
		});

		flushComboBox();
		flushTable();
	}

	private void flushTable() {
		members = BusController.getInstance().getPassengers(bus.getId());

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

	private void flushComboBox() {
		Member[] members = MemberController.getInstance().getAllMembers().stream()
				.filter(member -> !bus.getPassengers().contains(member.getId())).toArray(Member[]::new);
		comboBox.setModel(new DefaultComboBoxModel<Member>(members));
	}

}
