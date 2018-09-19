package com.window.panels.nodes.settings;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.engine.handlers.LanguageHandler;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelSettings extends PanelProtocol {

	private PanelSettingsLanguageSelection languageSelection;
	private PanelSettingsScreenResolutionSelection screenResolutionSelection;
	
	public PanelSettings(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 4641275705589733896L;
	private JButton buttonApply;
	
	@Override
	protected void initComponents() {
		setPanelName(languageHandler.getKey("tab_session_settings"));
		languageSelection = new PanelSettingsLanguageSelection(languageHandler);
		screenResolutionSelection = new PanelSettingsScreenResolutionSelection(languageHandler);
		buttonApply = new JButton(languageHandler.getKey("settings_button_apply"));
	}
	
	@Override
	protected void layoutComponents() {
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(setTitle(getPanelName()), BorderLayout.NORTH);
		panel1.add(languageSelection, BorderLayout.CENTER);
		panel1.add(screenResolutionSelection, BorderLayout.SOUTH);
		add(panel1, BorderLayout.NORTH);
		JPanel panelApply = new JPanel(new BorderLayout());
		panelApply.add(buttonApply, BorderLayout.EAST);
		add(panelApply, BorderLayout.SOUTH);
	}

	@Override
	protected void initListeners() {
		buttonApply.addActionListener(e -> {
			Popup.showInformationMessage(languageHandler.getKey("settings_restart_request"));
			languageSelection.execute();
			screenResolutionSelection.execute();
		});
	}

}