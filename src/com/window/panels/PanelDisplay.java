package com.window.panels;

import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.development.PanelConsole;

public class PanelDisplay extends PanelProtocol {

	private static final long serialVersionUID = 7990656968647138863L;
	
	private static JPanel cards;

	private PanelConsole panelConsole;
	
	public void addNodePanel(PanelProtocol panel) {
		String result = panel.getClass().getSimpleName();
		cards.add(panel, result.substring(0, 1).toLowerCase() + result.substring(1));
	}
	
	public void build() {
		add(cards);
		setState("panelWelcome");
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
		panelConsole = new PanelConsole();
		cards.add(panelConsole, "panelConsole");
	}

}