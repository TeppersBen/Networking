package com.window.panels.nodes.settings;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelSettingsLanguageSelection extends PanelProtocol {

	private static final long serialVersionUID = -4576385163781878932L;
	
	private JLabel labelLanguage;
	private JComboBox<String> languageBox;
	
	public PanelSettingsLanguageSelection(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	@Override
	protected void initComponents() {
		labelLanguage = new JLabel(languageHandler.getKey("settings_label_language") + ": ");
		languageBox = new JComboBox<>(getLanguageList());
		setCurrentLanguage();
	}

	@Override
	protected void layoutComponents() {
		add(labelLanguage, BorderLayout.WEST);
		add(languageBox, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {}
	
	private void setCurrentLanguage() {
		String item = "";
		for (int i = 0; i < languageBox.getItemCount(); i++) {
			languageBox.setSelectedIndex(i);
			item = languageBox.getSelectedItem().toString();
			if (item.equalsIgnoreCase(languageHandler.getKey("settings_language_english"))) {
				languageBox.setSelectedIndex(i);
			} else if (item.equalsIgnoreCase(languageHandler.getKey("settings_language_dutch"))) {
				languageBox.setSelectedIndex(i);
			}
		}
	}
	
	private String[] getLanguageList() {
		String[] result = {
				languageHandler.getKey("settings_language_english"),
				languageHandler.getKey("settings_language_dutch")
		};
		Arrays.sort(result);
		return result; 
	}

	public void execute() {
		String language = languageBox.getSelectedItem().toString();
		if (language.equalsIgnoreCase(languageHandler.getKey("settings_language_english"))) {
			languageHandler.changeLanguage("en", "GB");
		} else if (language.equalsIgnoreCase(languageHandler.getKey("settings_language_dutch"))) {
			languageHandler.changeLanguage("nl", "NL");
		}
	}
}