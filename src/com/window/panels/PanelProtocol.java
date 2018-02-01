package com.window.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.Settings;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.LookAndFeelManager;

public abstract class PanelProtocol extends JPanel {

	private static final long serialVersionUID = 3246826528396342033L;
	
	protected LanguageHandler languageHandler;
	private String panelName;
	
	public PanelProtocol(LanguageHandler languageHandler, String title) {
		this(languageHandler);
		setPanelName(title);
	}
	
	public PanelProtocol(LanguageHandler languageHandler) {
		setLayout(new BorderLayout());
		this.languageHandler = languageHandler;
		LookAndFeelManager.initLookAndFeel();
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
	

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	
	public String getPanelName() {
		return panelName;
	}
}