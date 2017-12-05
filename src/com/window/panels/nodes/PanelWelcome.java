package com.window.panels.nodes;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.Settings;
import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelWelcome extends PanelProtocol {

	private String welcome;
	private String version;
	private String releaseDate;
	
	public PanelWelcome(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = -7836952675902892073L;

	private void initStrings() {
		welcome = languageHandler.getKey("tab_welcome");
		version = languageHandler.getKey("word_version") + ": " + Settings.version;
		releaseDate = languageHandler.getKey("word_releaseDate") + ": " + Settings.releaseDate;
	}
	
	@Override
	protected void initComponents() {
		initStrings();
		setPanelName(welcome);
		setBorder(BorderFactory.createTitledBorder(""));
	}

	@Override
	protected void layoutComponents() {
		setLayout(new GridBagLayout());
		JPanel panelMain = new JPanel(new BorderLayout());
		panelMain.add(new JLabel("<html><b>" + welcome + "!", SwingConstants.HORIZONTAL), BorderLayout.NORTH);
		panelMain.add(new JLabel(toUpperCaseFirstChar(version), SwingConstants.HORIZONTAL), BorderLayout.CENTER);
		panelMain.add(new JLabel(toUpperCaseFirstChar(releaseDate), SwingConstants.HORIZONTAL), BorderLayout.SOUTH);
		add(panelMain);
	}

	private String toUpperCaseFirstChar(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
	
	protected void initListeners() {}

}
