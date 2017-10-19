package com.window.panels;

import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.development.PanelConsole;
import com.window.panels.nodes.PanelConverter;
import com.window.panels.nodes.PanelLanguage;
import com.window.panels.nodes.PanelLogging;
import com.window.panels.nodes.PanelWelcome;
import com.window.panels.nodes.vlsm.PanelVLSM;

public class PanelDisplay extends  PanelProtocol{

	private static final long serialVersionUID = 7990656968647138863L;
	
	private static JPanel cards;
	
	private PanelWelcome panelWelcome;
	private PanelConverter panelConverter;
	private PanelVLSM panelVLSM;
	private PanelLogging panelLogging;
	private PanelLanguage panelLanguage;
	private PanelConsole panelConsole;
	
	@Override
	protected void initComponents() {
		cards = new JPanel(new CardLayout());
		cards.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		
		panelWelcome = new PanelWelcome();
		panelConverter = new PanelConverter();
		panelVLSM = new PanelVLSM();
		panelLogging = new PanelLogging();
		panelLanguage = new PanelLanguage();
	}

	@Override
	protected void layoutComponents() {
		cards.add(panelWelcome, "panelWelcome");
		cards.add(panelConverter, "panelConverter");
		cards.add(panelVLSM, "panelVLSM");
		cards.add(panelLogging, "panelLogging");
		cards.add(panelLanguage, "panelLanguage");
		add(cards);
	}

	@Override
	protected void initListeners() {
		setState("panelWelcome");
	}
	
	public static void setState(String name) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, name);
	}
	
	@Override
	protected void initDebug() {
		if (!isDebug())
			return;
		panelConsole = new PanelConsole("DEVELOPMENT");
		cards.add(panelConsole, "panelConsole");
	}

}