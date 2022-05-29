package com.github.permissiondog.community.view;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.controller.BusController;
import com.github.permissiondog.community.model.Bus;
import com.github.permissiondog.community.model.enumeration.Cycle;
import com.github.permissiondog.community.model.enumeration.Direction;
import com.github.permissiondog.community.model.enumeration.Period;
import com.github.permissiondog.community.model.enumeration.RouteType;

public class ModifyBusFrame extends BusFrame {

	private static final long serialVersionUID = 1L;

	private Bus bus;
	
	public ModifyBusFrame(int id) {
		super();
		textFieldRouteCode.setEditable(false);
		
		bus = BusController.getInstance().getBus(id);
		
		textFieldRouteCode.setText(bus.getCode());
		textFieldName.setText(bus.getName());
		if (bus.getType().equals(RouteType.IN)) {
			rdbtnIn.setSelected(true);
		} else {
			rdbtnOut.setSelected(true);
		}
		if (bus.getDirection().equals(Direction.UP)) {
			rdbtnUp.setSelected(true);
		} else {
			rdbtnDown.setSelected(true);
		}
		
		comboBoxCycle.setSelectedItem(bus.getCycle());
		comboBoxPeriod.setSelectedItem(bus.getPeriod());
		
		textFieldDepartureTime.setText(bus.getDepartureTime().format(Constants.TIME_FORMATTER));
		textArea.setText(bus.getComment());
		
		setTitle("修改班车: " + bus.getName());
		
		btnConfirm.addActionListener(e -> {
			bus.setName(textFieldName.getText());
			bus.setType(rdbtnIn.isSelected() ? RouteType.IN : RouteType.OUT);
			bus.setDirection(rdbtnUp.isSelected() ? Direction.UP : Direction.DOWN);
			bus.setCycle((Cycle) comboBoxCycle.getSelectedItem());
			bus.setPeriod((Period) comboBoxPeriod.getSelectedItem());
			try {
				bus.setDepartureTime(
					LocalTime.parse(textFieldDepartureTime.getText(), Constants.TIME_FORMATTER));
			} catch (DateTimeParseException exception) {
				JOptionPane.showMessageDialog(this, "发车时间格式错误", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bus.setComment(textArea.getText());

			
			bus = BusController.getInstance().modifyBus(bus);
			if (bus != null) {
				JOptionPane.showMessageDialog(this, "修改成功", "信息", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
	}

}
