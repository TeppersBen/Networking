package com.window.panels.nodes;

import java.awt.BorderLayout;

import com.window.panels.PanelProtocol;

public class PanelVLSM extends PanelProtocol {

	private static final long serialVersionUID = -4200773632900692796L;

	@Override
	protected void initComponents() {
		
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("VLSM"), BorderLayout.NORTH);
	}

	@Override
	protected void initListeners() {
		
	}

}