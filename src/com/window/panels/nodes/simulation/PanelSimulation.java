package com.window.panels.nodes.simulation;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.engine.handlers.LanguageHandler;
import com.window.panels.PanelProtocol;

public class PanelSimulation extends PanelProtocol {

	private static final long serialVersionUID = 7162666289697482038L;
	private JScrollPane scroll;
	private JButton buttonAddDevice;
	private JPanel panelDevices;

	public PanelSimulation(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	@Override
	protected void initComponents() {
		setPanelName("Simulation");
		panelDevices = new JPanel(new GridLayout(5, 1));
		scroll = new JScrollPane();
		scroll.setViewportView(panelDevices);
		buttonAddDevice = new JButton("Add Device");
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("Simulation"), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(buttonAddDevice, BorderLayout.SOUTH);
	}

	@Override
	protected void initListeners() {
		buttonAddDevice.addActionListener(e -> {
			//scroll.add(new PanelDevice());
		});
	}

}