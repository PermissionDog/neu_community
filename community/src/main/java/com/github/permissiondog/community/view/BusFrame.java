package com.github.permissiondog.community.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.github.permissiondog.community.model.enumeration.Cycle;
import com.github.permissiondog.community.model.enumeration.Period;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;

/**
 * 班车信息
 * 
 * @author PermissionDog
 * @see NewBusFrame
 * @see ModifyBusFrame
 *
 */
public abstract class BusFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	protected JTextField textFieldRouteCode;
	protected JTextField textFieldDepartureTime;
	protected JTextField textFieldName;
	protected JRadioButton rdbtnIn;
	protected JRadioButton rdbtnOut;
	protected JComboBox<Cycle> comboBoxCycle;
	protected JRadioButton rdbtnUp;
	protected JRadioButton rdbtnDown;
	protected JComboBox<Period> comboBoxPeriod;
	protected JTextArea textArea;
	protected JButton btnConfirm;
	protected JLabel lblCodeWarn;

	public BusFrame() {
		setBounds(100, 100, 626, 436);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 560, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 350, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("线路代码");
		lblNewLabel.setBounds(10, 13, 75, 15);
		panel.add(lblNewLabel);
		
		textFieldRouteCode = new JTextField();
		textFieldRouteCode.setToolTipText("1-10位英文字母或数字");
		textFieldRouteCode.setBounds(95, 10, 163, 21);
		panel.add(textFieldRouteCode);
		textFieldRouteCode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("线路类型");
		lblNewLabel_1.setBounds(10, 65, 75, 15);
		panel.add(lblNewLabel_1);
		
		rdbtnIn = new JRadioButton("岛内班车");
		rdbtnIn.setSelected(true);
		rdbtnIn.setBounds(95, 61, 84, 23);
		panel.add(rdbtnIn);
		
		rdbtnOut = new JRadioButton("岛外班车");
		rdbtnOut.setBounds(184, 61, 84, 23);
		panel.add(rdbtnOut);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnIn);
		bg.add(rdbtnOut);
		
		JLabel lblNewLabel_1_1 = new JLabel("运营日期");
		lblNewLabel_1_1.setBounds(10, 114, 75, 15);
		panel.add(lblNewLabel_1_1);
		
		comboBoxCycle = new JComboBox<Cycle>();
		comboBoxCycle.setModel(new DefaultComboBoxModel<Cycle>(Cycle.values()));
		comboBoxCycle.setBounds(95, 110, 163, 23);
		panel.add(comboBoxCycle);
		
		JLabel lblNewLabel_2 = new JLabel("发车时间");
		lblNewLabel_2.setBounds(10, 165, 75, 15);
		panel.add(lblNewLabel_2);
		
		textFieldDepartureTime = new JTextField();
		textFieldDepartureTime.setToolTipText("如: 13:50, 15:03");
		textFieldDepartureTime.setColumns(10);
		textFieldDepartureTime.setBounds(95, 162, 163, 21);
		panel.add(textFieldDepartureTime);
		
		JLabel lblNewLabel_3 = new JLabel("线路名称");
		lblNewLabel_3.setBounds(291, 13, 75, 15);
		panel.add(lblNewLabel_3);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("1-10位英文字母、汉字或数字");
		textFieldName.setColumns(10);
		textFieldName.setBounds(376, 10, 163, 21);
		panel.add(textFieldName);
		
		JLabel lblNewLabel_1_2 = new JLabel("线路方向");
		lblNewLabel_1_2.setBounds(291, 65, 75, 15);
		panel.add(lblNewLabel_1_2);
		
		rdbtnUp = new JRadioButton("上行");
		rdbtnUp.setSelected(true);
		rdbtnUp.setBounds(376, 61, 84, 23);
		panel.add(rdbtnUp);
		
		
		rdbtnDown = new JRadioButton("下行");
		rdbtnDown.setBounds(465, 61, 84, 23);
		panel.add(rdbtnDown);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdbtnUp);
		bg2.add(rdbtnDown);
		
		comboBoxPeriod = new JComboBox<Period>();
		comboBoxPeriod.setModel(new DefaultComboBoxModel<Period>(Period.values()));
		comboBoxPeriod.setBounds(376, 110, 163, 23);
		panel.add(comboBoxPeriod);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("运营时段");
		lblNewLabel_1_1_1.setBounds(291, 114, 75, 15);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("备注");
		lblNewLabel_4.setBounds(291, 165, 75, 15);
		panel.add(lblNewLabel_4);
		
		textArea = new JTextArea();
		textArea.setToolTipText("200字符以内");
		textArea.setBounds(376, 160, 163, 111);
		panel.add(textArea);
		
		btnConfirm = new JButton("确定");
		btnConfirm.setBounds(456, 305, 93, 23);
		panel.add(btnConfirm);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.setBounds(295, 305, 93, 23);
		panel.add(btnCancel);
		
		lblCodeWarn = new JLabel("");
		lblCodeWarn.setForeground(Color.RED);
		lblCodeWarn.setBounds(95, 40, 163, 15);
		panel.add(lblCodeWarn);
		
		
		//取消
		btnCancel.addActionListener(e -> dispose());
	}
}
