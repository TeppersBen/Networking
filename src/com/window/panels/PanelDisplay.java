package com.window.panels;

import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.development.PanelConsole;
import com.handlers.LanguageHandler;

public class PanelDisplay extends PanelProtocol {

	public PanelDisplay(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 7990656968647138863L;
	
	private static JPanel cards;

	private PanelConsole panelConsole;
	
	public void addNodePanel(PanelProtocol panel) {
		cards.add(panel, "panel" + panel.getPanelName());
	}
	
	public void build() {
		add(cards);
		setState("panel" + languageHandler.getKey("tab_welcome"));
	}
	
	@Override
	protected void initComponents() {
		cards = new JPanel(new CardLayout());
		cards.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
	}

	protected void layoutComponents() {}
	protected void initListeners() {}
	
	public static void setState(String name) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, name);
	}
	
	@Override
	protected void initDebug() {
		if (!isDebug())
			return;
		panelConsole = new PanelConsole(languageHandler);
		cards.add(panelConsole, "panelConsole");
	}

}