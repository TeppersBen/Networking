package com.window.panels.nodes.settings;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelSettings extends PanelProtocol {

	public PanelSettings(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 4641275705589733896L;
	private JButton buttonApply;
	
	@Override
	protected void initComponents() {
		buttonApply = new JButton(languageHandler.getKey("settings_button_apply"));
	}
	
	@Override
	protected void layoutComponents() {
		add(setTitle("Settings"), BorderLayout.NORTH);
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(new PanelSettingsLanguageSelection(languageHandler), BorderLayout.NORTH);
		add(panel1, BorderLayout.CENTER);
		JPanel panelApply = new JPanel(new BorderLayout());
		panelApply.add(buttonApply, BorderLayout.EAST);
		add(panelApply, BorderLayout.SOUTH);
		
	}

	@Override
	protected void initListeners() {
		buttonApply.addActionListener(e -> {
			/*
			 * TODO Modify the language pack.
			 */
		});
	}

}