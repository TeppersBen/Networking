package com.engine.utils;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelAutoSorter extends JPanel {

	private static final long serialVersionUID = -7626734308734596512L;

	private JPanel[] neededPanels;
	
	public PanelAutoSorter(JPanel... panels) {
		setLayout(new BorderLayout());
		setRequiredPanels(panels.length);
	}
	
	private void setRequiredPanels(int length) {
		neededPanels = new JPanel[length/2];
		for (JPanel p : neededPanels) {
			p.setLayout(new BorderLayout());
		}
	}
	
}
