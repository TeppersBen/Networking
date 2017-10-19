package com.window.panels.nodes;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.utils.Logger;
import com.window.panels.PanelProtocol;

public class PanelLogging extends PanelProtocol {

	private static final long serialVersionUID = 5475597876727660349L;

	private JTextPane loggingField;
	private JScrollPane scroll;
	
	@Override
	protected void initComponents() {
		loggingField = new JTextPane();
		Logger.setLoggingField(loggingField);
		scroll = new JScrollPane();
		scroll.setViewportView(loggingField);
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("Logging"), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {
		
	}

}
