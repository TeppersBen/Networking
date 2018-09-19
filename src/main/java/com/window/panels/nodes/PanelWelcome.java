package com.window.panels.nodes;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import com.Settings;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.PanelDetailedInformation;
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
	}

	@Override
	protected void layoutComponents() {
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new GridBagLayout());
		add(new PanelDetailedInformation(toUpperCaseFirstChar(welcome) + "!",
										 toUpperCaseFirstChar(version),
										 toUpperCaseFirstChar(releaseDate)));
	}

	private String toUpperCaseFirstChar(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}
	
	protected void initListeners() {}

}
