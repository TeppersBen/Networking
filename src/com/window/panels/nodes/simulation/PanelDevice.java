package com.window.panels.nodes.simulation;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDevice extends JPanel {

	private static final long serialVersionUID = 2258646838672190191L;

	private JLabel labelDeviceName;
	
	public PanelDevice() {
		initComponents();
		layoutComponents();
	}
	
	private void initComponents() {
		labelDeviceName = new JLabel("[DEVICE_NAME]");
	}
	
	private void layoutComponents() {
		add(labelDeviceName);
	}
	
}