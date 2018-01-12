package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelConverter extends PanelProtocol {

	public PanelConverter(LanguageHandler languageHandler) {
		super(languageHandler);
	}
	private static final long serialVersionUID = -5111118154502890039L;

	@Override
	protected void layoutComponents() {
		setLayout(new BorderLayout());
		
		/* assembly */
		JPanel sub1 = new JPanel(new BorderLayout());
		sub1.add(new PanelConverterAddress(languageHandler), BorderLayout.NORTH);
		sub1.add(new PanelConverterNetmask(languageHandler), BorderLayout.CENTER);
		sub1.add(new PanelConverterRequestedHosts(languageHandler), BorderLayout.SOUTH);
		
		JPanel acl = new JPanel(new BorderLayout());
		acl.add(new PanelConverterACL(languageHandler), BorderLayout.NORTH);
		acl.add(new JLabel(" "), BorderLayout.CENTER);
		
		JPanel sub2 = new JPanel(new BorderLayout());
		sub2.add(sub1, BorderLayout.NORTH);
		sub2.add(acl, BorderLayout.CENTER);
		
		add(setTitle(getPanelName()), BorderLayout.NORTH);
		add(sub2, BorderLayout.CENTER);
		add(new JLabel(" "), BorderLayout.SOUTH);
	}

	protected void initListeners() {}
	
	protected void initComponents() {
		setPanelName(languageHandler.getKey("tab_calculators_converter"));
	}
}