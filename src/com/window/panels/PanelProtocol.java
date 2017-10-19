package com.window.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.Settings;

public abstract class PanelProtocol extends JPanel {

	private static final long serialVersionUID = 3246826528396342033L;
	
	public PanelProtocol() {
		setLayout(new BorderLayout());
		initComponents();
		initDebug();
		layoutComponents();
		initListeners();
	}

	protected abstract void initComponents();
	protected abstract void layoutComponents();
	protected abstract void initListeners();
	
	protected void initDebug() {}
	protected boolean isDebug() { return Settings.debug; }
	
	protected void setFieldSet(JPanel panel, String title) {
		panel.setBorder(BorderFactory.createTitledBorder(title));
	}
	
	protected void setEmptyFieldSet(JPanel panel) {
		panel.setBorder(BorderFactory.createTitledBorder(""));
	}
	
	protected JPanel setTitle(String title) {
		JPanel panelTitle = new JPanel(new BorderLayout());
		panelTitle.setBorder(BorderFactory.createTitledBorder(""));
		JLabel labelTitle = new JLabel("...");
		panelTitle.add(labelTitle);
		labelTitle.setText(title);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setVerticalAlignment(SwingConstants.CENTER);
		return panelTitle;
	}
}