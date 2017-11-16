package com.window.panels.nodes.simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

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
	
	private List<PanelDevice> list = new LinkedList<>();
	
	private final int MAX_DEVICES = 10;
	private int deviceCount;
	
	public PanelSimulation(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	@Override
	protected void initComponents() {
		setPanelName("Simulation");
		panelDevices = new JPanel(new GridLayout(10, 1));
		scroll = new JScrollPane(panelDevices);
		panelDevices.setMaximumSize(new Dimension(200, 500));
		buttonAddDevice = new JButton("Add Device");
	}

	@Override
	protected void layoutComponents() {
		add(setTitle(getPanelName()), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(buttonAddDevice, BorderLayout.SOUTH);
	}

	@Override
	protected void initListeners() {
		buttonAddDevice.addActionListener(e -> {
			if (deviceCount >= MAX_DEVICES) return;
			PanelDevice device = new PanelDevice();
			list.add(device);
			panelDevices.add(device);
			panelDevices.revalidate();
			deviceCount++;
		});
	}
}