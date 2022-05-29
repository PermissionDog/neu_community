package com.github.permissiondog.community.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.BusController;
import com.github.permissiondog.community.controller.UserController;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.User;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LogisticsManagerMainFrame extends MainFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private List<Bus> buses;

	public LogisticsManagerMainFrame(User user) {
		super(user);
		
		setTitle("后勤管理员: " + user.getName());
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 705, 422);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 600, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 40, 220, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 585, 182);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "\u7EBF\u8DEF\u4EE3\u7801", "\u7EBF\u8DEF\u540D\u79F0", "\u65B9\u5411", "\u8FD0\u8425\u65E5\u671F", "\u8FD0\u8425\u65F6\u6BB5", "\u53D1\u8F66\u65F6\u95F4", "\u622A\u6B62\u65F6\u95F4", "\u9884\u7EA6\u4EBA\u6570"
			}
		) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		

		JButton btnReturn = new JButton("返回");
		btnReturn.setBounds(467, 277, 93, 23);
		panel.add(btnReturn);
		
		JButton btnDeleteBus = new JButton("删除班车");
		btnDeleteBus.setBounds(10, 52, 105, 23);
		panel.add(btnDeleteBus);
		
		JButton btnSetExpireTime = new JButton("设置截止时间");
		btnSetExpireTime.setBounds(240, 52, 131, 23);
		panel.add(btnSetExpireTime);
		
		JButton btnShowPassengers = new JButton("查看乘客");
		btnShowPassengers.setBounds(490, 52, 105, 23);
		panel.add(btnShowPassengers);
		
		JButton btnNewBus = new JButton("新建班车");
		btnNewBus.setBounds(10, 19, 105, 23);
		panel.add(btnNewBus);
		
		JButton btnModifyBus = new JButton("修改班车");
		btnModifyBus.setBounds(125, 52, 105, 23);
		panel.add(btnModifyBus);
		

		//返回
		btnReturn.addActionListener(e -> {
			UserController.getInstance().showLoginFrame();
			dispose();
		});
		
		//新增班车
		btnNewBus.addActionListener(e -> BusController.getInstance().showNewBusFrame());
		
		//删除班车
		btnDeleteBus.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "请先选择要删除的用户");
				return;
			}
			int result = JOptionPane.showConfirmDialog(LogisticsManagerMainFrame.this, "是否要删除", "", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				return;
			}

			int[] ids = Arrays.stream(table.getSelectedRows()).map(i -> buses.get(i).getId()).toArray();
			Arrays.stream(ids).forEach(id -> {
				if (BusController.getInstance().deleteBus(id) == null) {
					JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			});
			
			JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		});
		
		//修改班车
		btnModifyBus.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "请先选择要修改的班车");
				return;
			}
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "最多选择一个要修改的班车");
				return;
			}
			int id = buses.get(table.getSelectedRow()).getId();
			BusController.getInstance().showModifyBusFrame(id);
		});
		
		//设置截止时间
		btnSetExpireTime.addActionListener(e -> {
			if (table.getSelectedRowCount() < 1) {
				JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "请先选择要设置时间的班车");
				return;
			}
			if (table.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(LogisticsManagerMainFrame.this, "最多选择一个要设置的班车");
				return;
			}
			int id = buses.get(table.getSelectedRow()).getId();
			BusController.getInstance().showSetExpireTimeFrame(id);
		});
		
		//注册观察者
		BusController.getInstance().registerObeserver(this::flushTable);
		
		flushTable();
	}
	
	private void flushTable() {
		buses = BusController.getInstance().getAllBuses();
		Object[][] data = buses.stream().map(bus -> {
			Object[] obj = new Object[9];
			obj[0] = bus.getId();
			obj[1] = bus.getCode();
			obj[2] = bus.getName();
			obj[3] = bus.getDirection();
			obj[4] = bus.getCycle();
			obj[5] = bus.getPeriod();
			obj[6] = bus.getDepartureTime().format(Constants.TIME_FORMATTER);
			if (bus.getExpireTime() == null) {
				obj[7] = "未确认";
			} else {
				obj[7] = bus.getExpireTime().format(Constants.TIME_FORMATTER);
			}
			obj[8] = bus.getPassengers().size();
			return obj;
		}).toArray(Object[][]::new);
		
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"ID", "\u7EBF\u8DEF\u4EE3\u7801", "\u7EBF\u8DEF\u540D\u79F0", "\u65B9\u5411", "\u8FD0\u8425\u65E5\u671F", "\u8FD0\u8425\u65F6\u6BB5", "\u53D1\u8F66\u65F6\u95F4", "\u622A\u6B62\u65F6\u95F4", "\u9884\u7EA6\u4EBA\u6570"
				}
			) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
	}
}
