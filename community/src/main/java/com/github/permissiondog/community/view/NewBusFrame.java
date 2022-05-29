package com.github.permissiondog.community.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.BusController;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.enumeration.*;

public class NewBusFrame extends BusFrame {

	private static final long serialVersionUID = 1L;

	public NewBusFrame() {
		super();
		setTitle("创建班车");
		
		textFieldRouteCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Bus b = BusController.getInstance().getBus(textFieldRouteCode.getText());
				if (b != null) {
					lblCodeWarn.setText("路线代码已存在");
				} else {
					lblCodeWarn.setText("");
				}
			}
		});
		
		btnConfirm.addActionListener(e -> {
			Bus bus = new Bus();
			bus.setCode(textFieldRouteCode.getText());
			bus.setName(textFieldName.getText());
			bus.setType(rdbtnIn.isSelected() ? RouteType.IN : RouteType.OUT);
			bus.setDirection(rdbtnUp.isSelected() ? Direction.UP : Direction.DOWN);
			bus.setCycle((Cycle) comboBoxCycle.getSelectedItem());
			bus.setPeriod((Period) comboBoxPeriod.getSelectedItem());
			try {
				bus.setDepartureTime(
					LocalTime.parse(textFieldDepartureTime.getText(), Constants.TIME_FORMATTER));
			} catch (DateTimeParseException exception) {
				JOptionPane.showMessageDialog(NewBusFrame.this, "发车时间格式错误", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bus.setComment(textArea.getText());
			

			
			bus = BusController.getInstance().newBus(bus);
			if (bus != null) {
				JOptionPane.showMessageDialog(NewBusFrame.this, "创建成功", "信息", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
	}

}
