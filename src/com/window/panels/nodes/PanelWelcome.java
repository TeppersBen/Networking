package com.window.panels.nodes;

import com.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelWelcome extends PanelProtocol {

	public PanelWelcome(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = -7836952675902892073L;

	@Override
	protected void initComponents() {
		setPanelName(languageHandler.getKey("tab_welcome"));
	}

	@Override
	protected void layoutComponents() {
		add(setTitle(languageHandler.getKey("tab_welcome") + "!"));
	}

	@Override
	protected void initListeners() {
		
	}

}
