package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.handlers.LanguageHandler;
import com.utils.Popup;
import com.utils.calculators.NetworkConverter;
import com.utils.calculators.VLSMSpecializedCalculator;
import com.window.panels.PanelProtocol;

public class PanelConverterRequestedHosts extends PanelProtocol {

	public PanelConverterRequestedHosts(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 2371930704559590937L;

	private JLabel labelHosts;
	private JLabel labelHostsResultCIDR;
	private JLabel labelHostsResultNetmask;
	private JLabel labelHostsClass;
	private JLabel labelHostsTotalHosts;
	private JLabel labelHostsTotalSubnets;
	private JTextField textfieldHosts;
	private JButton buttonHosts;
	private JButton buttonHelp;
	
	@Override
	protected void initComponents() {
		labelHosts = new JLabel(" " + languageHandler.getKey("converter_hosts_label_RequestedHosts") + ": ");
		labelHostsResultCIDR = new JLabel(" " + languageHandler.getKey("converter_hosts_label_CIDR") + ": ");
		labelHostsResultNetmask = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Netmask") + ": ");
		labelHostsClass = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Class") + ": ");
		labelHostsTotalSubnets = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Subnets") + ": ");
		labelHostsTotalHosts = new JLabel(" " + languageHandler.getKey("converter_hosts_label_Hosts") + ": ");
		textfieldHosts = new JTextField(11);
		buttonHosts = new JButton(languageHandler.getKey("converter_hosts_button_ConvertHosts"));
		buttonHelp = new JButton(languageHandler.getKey("converter_button_Help"));
	}

	@Override
	protected void layoutComponents() {
		JPanel panelHosts = new JPanel(new BorderLayout());
		JPanel panelHostsSub = new JPanel(new BorderLayout());
		JPanel panelHostsResult = new JPanel(new BorderLayout());
		JPanel panelHostsResult1 = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new BorderLayout());
		panelButtons.add(buttonHosts, BorderLayout.CENTER);
		panelButtons.add(buttonHelp, BorderLayout.EAST);
		setEmptyFieldSet(panelHosts);
		panelHostsSub.add(labelHosts, BorderLayout.WEST);
		panelHostsSub.add(textfieldHosts, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsResultCIDR, BorderLayout.NORTH);
		panelHostsResult.add(labelHostsResultNetmask, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsClass, BorderLayout.SOUTH);
		panelHostsResult1.add(labelHostsTotalSubnets, BorderLayout.NORTH);
		panelHostsResult1.add(labelHostsTotalHosts, BorderLayout.CENTER);
		panelHostsResult1.add(panelButtons, BorderLayout.SOUTH);
		panelHosts.add(panelHostsSub, BorderLayout.NORTH);
		panelHosts.add(panelHostsResult, BorderLayout.CENTER);
		panelHosts.add(panelHostsResult1, BorderLayout.SOUTH);
		add(panelHosts);
	}

	@Override
	protected void initListeners() {
		buttonHosts.addActionListener(e -> {
			if (!isValidInput())
				return;
			int value = Integer.parseInt(textfieldHosts.getText());
			labelHostsTotalHosts.setText(" " + languageHandler.getKey("converter_hosts_label_Hosts") + ": " + VLSMSpecializedCalculator.getValidHost(value));
			labelHostsResultCIDR.setText(" " + languageHandler.getKey("converter_hosts_label_CIDR") + ": " + VLSMSpecializedCalculator.getCIDR(value));
			labelHostsResultNetmask.setText(" " + languageHandler.getKey("converter_hosts_label_Netmask") + ": " + VLSMSpecializedCalculator.getNetmask(value));
			labelHostsClass.setText(" " + languageHandler.getKey("converter_hosts_label_Class") + ": " + NetworkConverter.getNetmaskClass(VLSMSpecializedCalculator.getNetmask(value)));
			labelHostsTotalSubnets.setText(" " + languageHandler.getKey("converter_hosts_label_Subnets") + ": " + NetworkConverter.getTotalValidSubnets(Integer.parseInt(VLSMSpecializedCalculator.getCIDR(value))));
		});
		buttonHelp.addActionListener(e -> {
			showHelp();
		});
	}
	
	private boolean isValidInput() {
		int value = 0;
		try {
			value = Integer.parseInt(textfieldHosts.getText());
		} catch (NumberFormatException ex) {
			Popup.showErrorMessage(languageHandler.getKey("converter_hosts_error_invalidHosts"));
			return false;
		}
		if (value < 0) {
			Popup.showErrorMessage(languageHandler.getKey("converter_hosts_error_invalidHosts"));
			return false;
		}
		return true;
	}

	private void showHelp() {
		Popup.showHelpMessage(languageHandler.getKey("converter_hosts_help_WhatIsIt"), 
				languageHandler.getKey("converter_hosts_help_HowDoesItWork"),
				languageHandler.getKey("converter_hosts_help_Example"));
	}
}