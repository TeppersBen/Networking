package com.window.panels.nodes.simulation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDevice extends JPanel {

	private static final long serialVersionUID = 2258646838672190191L;

	private JLabel labelDeviceName;
	private JButton buttonConsole;
	private JButton	buttonRemoveDevice;
	
	public PanelDevice() {
		initComponents();
		layoutComponents();
	}
	
	private void initComponents() {
		labelDeviceName = new JLabel("[DEVICE_NAME]");
		buttonConsole = new JButton("[OPEN_CONSOLE]");
		buttonRemoveDevice = new JButton("[REMOVE]");
	}
	
	private void layoutComponents() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(buttonConsole);
		panel.add(buttonRemoveDevice);
		add(labelDeviceName, BorderLayout.NORTH);
		add(panel, BorderLayout.SOUTH);
	}
	
	public JButton getButtonRemove() {
		return buttonRemoveDevice;
	}
}