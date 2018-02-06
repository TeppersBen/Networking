package com.window.panels.nodes.settings;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.engine.handlers.ConfigurationHandler;
import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelSettingsScreenResolutionSelection extends PanelProtocol {

	private static final long serialVersionUID = 1335234267806157509L;

	private JLabel label;
	private JComboBox<String> resolutions;
	
	public PanelSettingsScreenResolutionSelection(LanguageHandler languageHandler) {
		super(languageHandler);
		selectCurrentResolution();
	}

	@Override
	protected void initComponents() {
		label = new JLabel(languageHandler.getKey("settings_resolution") + ": ");
		resolutions = new JComboBox<>(new String[] { "520 x 520", "800 x 520", "800 x 600", "1024 x 768", "1152 x 864" });
	}

	@Override
	protected void layoutComponents() {
		add(label, BorderLayout.WEST);
		add(resolutions, BorderLayout.CENTER);
	}

	protected void initListeners() {}
	
	public void selectCurrentResolution() {
		String width = ConfigurationHandler.getUserKey("windowWidth");
		String height = ConfigurationHandler.getUserKey("windowHeight");
		for (int i = 0; i < resolutions.getItemCount(); i++) {
			if (resolutions.getItemAt(i).equalsIgnoreCase(width + " x " + height))
				resolutions.setSelectedIndex(i);
		}
	}
	
	public void execute() {
		String width = ((String)resolutions.getSelectedItem()).split("x")[0].trim();
		String height = ((String)resolutions.getSelectedItem()).split("x")[1].trim();
		ConfigurationHandler.modifyUserKey("windowWidth", width);
		ConfigurationHandler.modifyUserKey("windowHeight", height);
	}
}