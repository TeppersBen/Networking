package com.window.panels.nodes;

import java.awt.BorderLayout;

import com.window.panels.PanelProtocol;

public class PanelSettings extends PanelProtocol {

	private static final long serialVersionUID = 4641275705589733896L;

	@Override
	protected void initComponents() {
		
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("Settings"), BorderLayout.NORTH);
	}

	@Override
	protected void initListeners() {
		
	}

}