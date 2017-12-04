package com.window.panels.nodes;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelWelcome extends PanelProtocol {

	public PanelWelcome(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = -7836952675902892073L;

	@Override
	protected void initComponents() {
		setPanelName(languageHandler.getKey("tab_welcome"));
		setBorder(BorderFactory.createTitledBorder(""));
	}

	@Override
	protected void layoutComponents() {
		add(new JLabel("<html>" + languageHandler.getKey("tab_welcome") + "!", SwingConstants.HORIZONTAL));
	}

	@Override
	protected void initListeners() {
		
	}

}
