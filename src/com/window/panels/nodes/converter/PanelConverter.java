package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.window.panels.PanelProtocol;

public class PanelConverter extends PanelProtocol {

	private static final long serialVersionUID = -5111118154502890039L;

	@Override
	protected void layoutComponents() {
		setLayout(new BorderLayout());
		
		/* assembly */
		JPanel sub1 = new JPanel(new BorderLayout());
		sub1.add(new PanelConverterAddress(), BorderLayout.NORTH);
		sub1.add(new PanelConverterNetmask(), BorderLayout.CENTER);
		sub1.add(new PanelConverterRequestedHosts(), BorderLayout.SOUTH);
		
		JPanel sub2 = new JPanel(new BorderLayout());
		sub2.add(sub1, BorderLayout.NORTH);
		sub2.add(new JLabel(" "), BorderLayout.CENTER);
		
		add(setTitle("Converter"), BorderLayout.NORTH);
		add(sub2, BorderLayout.CENTER);
	}

	protected void initListeners() {}
	protected void initComponents() {}
}