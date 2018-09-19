package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.calculators.NetworkConverter;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.handlers.ValidatorHandler;
import com.engine.utils.Formatter;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelConverterNetmask extends PanelProtocol {

	public PanelConverterNetmask(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 8395957340899010044L;
	
	private JLabel labelNetmask;
	private JLabel labelNetmaskResult;
	private JLabel labelNetmaskClass;
	private JLabel labelNetmaskTotalHosts;
	private JLabel labelNetmaskTotalSubnets;
	private JLabel labelNetmaskWildcard;
	private TextField textfieldNetmask;
	private JButton buttonNetmask;
	private JButton buttonHelp;
	
	@Override
	protected void initComponents() {
		labelNetmask = new JLabel(" " + languageHandler.getKey("converter_netmask_label_NetmaskCIDR") + ": ");
		labelNetmaskResult = new JLabel(" " + languageHandler.getKey("converter_netmask_label_NetmaskCIDR") + ": ");
		labelNetmaskClass = new JLabel(" " + languageHandler.getKey("converter_netmask_label_Class") + ": ");
		labelNetmaskTotalSubnets = new JLabel(" " + languageHandler.getKey("converter_netmask_label_Subnets") + ": ");
		labelNetmaskTotalHosts = new JLabel(" " + languageHandler.getKey("converter_netmask_label_Hosts") + ": ");
		textfieldNetmask = new TextField(11, languageHandler.getKey("word_example(short)") + ": [255.255.0.0 | 16]");
		buttonNetmask = new JButton(languageHandler.getKey("converter_netmask_button_ConverterNetmask"));
		buttonHelp = new JButton(languageHandler.getKey("converter_button_Help"));
		labelNetmaskWildcard = new JLabel(" " + languageHandler.getKey("converter_acl_wildcard") + ": ");
	}
	
	@Override
	protected void layoutComponents() {
		JPanel panelNetmask = new JPanel(new BorderLayout());
		JPanel panelNetmaskSub = new JPanel(new BorderLayout());
		JPanel panelNetmaskResult = new JPanel(new BorderLayout());
		JPanel panelNetmaskResult1 = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelNetmask);
		panelButtons.add(buttonNetmask, BorderLayout.CENTER);
		panelButtons.add(buttonHelp, BorderLayout.EAST);
		panelNetmaskSub.add(labelNetmask, BorderLayout.WEST);
		panelNetmaskSub.add(textfieldNetmask, BorderLayout.CENTER);
		panelNetmaskResult.add(labelNetmaskResult, BorderLayout.NORTH);
		panelNetmaskResult.add(labelNetmaskClass, BorderLayout.CENTER);
		panelNetmaskResult.add(labelNetmaskTotalSubnets, BorderLayout.SOUTH);
		panelNetmaskResult1.add(labelNetmaskTotalHosts, BorderLayout.NORTH);
		panelNetmaskResult1.add(labelNetmaskWildcard, BorderLayout.CENTER);
		panelNetmaskResult1.add(panelButtons, BorderLayout.SOUTH);
		panelNetmask.add(panelNetmaskSub, BorderLayout.NORTH);
		panelNetmask.add(panelNetmaskResult, BorderLayout.CENTER);
		panelNetmask.add(panelNetmaskResult1, BorderLayout.SOUTH);
		add(panelNetmask);
	}
	
	@Override
	protected void initListeners() {
		buttonNetmask.addActionListener(e -> {
			if (isCIDRNotation()) {
				if (!ValidatorHandler.isValidCIDR(textfieldNetmask.getText())) {
					sendErrorMessage();
					return;
				}
				sendOutput(true, false);
			} else if (isBinary()) {
				if (!isValidBinaryInput()) {
					sendErrorMessage();
					return;
				}
				sendOutput(false, true);
			} else {
				if (!ValidatorHandler.isValidNetmask(textfieldNetmask.getText())) {
					sendErrorMessage();
					return;
				}
				sendOutput(false, false);
			}
		});
		buttonHelp.addActionListener(e -> {
			showHelp();
		});
	}
	private boolean isCIDRNotation() {
		if (textfieldNetmask.getText().split("\\.").length != 1)
			return false;
		return true;
	}
	
	private boolean isBinary() {
		String[] splitter = textfieldNetmask.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (splitter[i].length() != 8)
				return false;
		}
		return true;
	}
	
	private boolean isValidBinaryInput() {
		String[] splitter = textfieldNetmask.getText().split("\\.");
		if (splitter.length != 4)
			return false;
		for (int i = 0; i < 4; i++) {
			try {
				Integer.parseInt(splitter[i]);
			} catch (NumberFormatException ex) {
				return false;
			}
		}
		return true;
	}
	
	private void sendErrorMessage() {
		Popup.showErrorMessage(languageHandler.getKey("converter_netmask_error_invalidNetmask") + "<br>"
				+ languageHandler.getKey("word_example") + ": (" + languageHandler.getKey("word_decimal") + ") " + languageHandler.getKey("converter_netmask_error_decimal_Example") + "<br>"
				+ languageHandler.getKey("word_example") + ": (" + languageHandler.getKey("word_binary") + ") " + languageHandler.getKey("converter_netmask_error_binary_Example") + "<br>"
				+ languageHandler.getKey("word_example") + ": (CIDR) " + languageHandler.getKey("converter_netmask_error_cidr_Example"));
	}
	
	private void sendOutput(boolean isCIDR, boolean isBinary) {
		if (isCIDR) {
			int netmask = Integer.parseInt(textfieldNetmask.getText());
			labelNetmaskResult.setText(" " + languageHandler.getKey("converter_netmask_label_Netmask") + ": " + NetworkConverter.netmaskCIDRtoDecimal(netmask));
			labelNetmaskClass.setText(" " + languageHandler.getKey("converter_netmask_label_Class") + ": " + NetworkConverter.getNetmaskClass(NetworkConverter.netmaskCIDRtoDecimal(netmask)));
			labelNetmaskTotalSubnets.setText(" " + languageHandler.getKey("converter_netmask_label_Subnets") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(netmask)));
			labelNetmaskTotalHosts.setText(" " + languageHandler.getKey("converter_netmask_label_Hosts") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(netmask)));
			labelNetmaskWildcard.setText(" " + languageHandler.getKey("converter_acl_wildcard") + ": " + NetworkConverter.getWildCardMask(netmask));
		} else {
			if (isBinary) {
				String netmask = NetworkConverter.binaryIPv4ToDecimal(textfieldNetmask.getText());
				if (!NetworkConverter.isValidNetmask(netmask)) {
					Popup.showErrorMessage(languageHandler.getKey("converter_netmask_error_invalidNetmask"));
					return;
				}
				labelNetmaskResult.setText("<html>&#160;" + languageHandler.getKey("converter_netmask_label_Netmask") + ": " + NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask))) + "<br>"
											+ "&#160;" + languageHandler.getKey("converter_netmask_label_CIDR") + ": " + NetworkConverter.netmaskDecimalToCIDR(netmask) + "</html>");
				labelNetmaskClass.setText(" " + languageHandler.getKey("converter_netmask_label_Class") + ": " + NetworkConverter.getNetmaskClass(netmask));
				labelNetmaskTotalSubnets.setText(" " + languageHandler.getKey("converter_netmask_label_Subnets") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskTotalHosts.setText(" " + languageHandler.getKey("converter_netmask_label_Hosts") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskWildcard.setText(" " + languageHandler.getKey("converter_acl_wildcard") + ": " + NetworkConverter.getWildCardMask(netmask));
			} else {
				String netmask = textfieldNetmask.getText();
				if (!NetworkConverter.isValidNetmask(netmask)) {
					Popup.showErrorMessage(languageHandler.getKey("converter_netmask_error_invalidNetmask"));
					return;
				}
				labelNetmaskResult.setText(" " + languageHandler.getKey("converter_netmask_label_CIDR") + ": " + NetworkConverter.netmaskDecimalToCIDR(netmask));
				labelNetmaskClass.setText(" " + languageHandler.getKey("converter_netmask_label_Class") + ": " + NetworkConverter.getNetmaskClass(netmask));
				labelNetmaskTotalSubnets.setText(" " + languageHandler.getKey("converter_netmask_label_Subnets") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskTotalHosts.setText(" " + languageHandler.getKey("converter_netmask_label_Hosts") + ": " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskWildcard.setText(" " + languageHandler.getKey("converter_acl_wildcard") + ": " + NetworkConverter.getWildCardMask(netmask));
			}
		}
	}
	
	private void showHelp() {
		Popup.showHelpMessage(languageHandler.getKey("converter_netmask_help_WhatIsIt"), 
				languageHandler.getKey("converter_netmask_help_HowDoesItWork"), 
				languageHandler.getKey("converter_netmask_help_Example"));
	}
}