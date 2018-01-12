package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.calculators.NetworkConverter;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelConverterAddress extends PanelProtocol {

	public PanelConverterAddress(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 1L;
	private JLabel labelAddress;
	private JLabel labelAddressResult;
	private TextField textfieldAddress;
	private JButton buttonAddress;
	private JButton buttonHelp;
	
	@Override
	protected void initComponents() {
		labelAddress = new JLabel(" " + languageHandler.getKey("converter_IPv4_label_IPv4_Address") + ": ");
		labelAddressResult = new JLabel(" " + languageHandler.getKey("converter_IPv4_label_Address") + ": ");
		textfieldAddress = new TextField(11, "ex: 192.168.0.1");
		buttonAddress = new JButton(languageHandler.getKey("converter_IPv4_button_ConvertAddress"));
		buttonHelp = new JButton(languageHandler.getKey("converter_button_Help"));
	}

	@Override
	protected void layoutComponents() {
		JPanel panelAddress = new JPanel(new BorderLayout());
		JPanel panelAddressSub = new JPanel(new BorderLayout());
		JPanel panelAddressResult = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelAddress);
		panelButtons.add(buttonAddress, BorderLayout.CENTER);
		panelButtons.add(buttonHelp, BorderLayout.EAST);
		panelAddressSub.add(labelAddress, BorderLayout.WEST);
		panelAddressSub.add(textfieldAddress, BorderLayout.CENTER);
		panelAddressResult.add(labelAddressResult);
		panelAddress.add(panelAddressSub, BorderLayout.NORTH);
		panelAddress.add(panelAddressResult, BorderLayout.CENTER);
		panelAddress.add(panelButtons, BorderLayout.SOUTH);
		add(panelAddress);
	}

	private void sendErrorMessage() {
		Popup.showErrorMessage(languageHandler.getKey("converter_IPv4_error_invalidIPv4Address") + "<br>"
				+ "Example: (decimal) " + languageHandler.getKey("converter_IPv4_error_decimal_Example") + "<br>"
				+ "Example: (binary) " + languageHandler.getKey("converter_IPv4_error_binary_Example"));
	}
	
	@Override
	protected void initListeners() {		
		buttonAddress.addActionListener(e -> {
			if (!isValidInput()) {
				sendErrorMessage();
				return;
			}
			if (isBinaryInput()) {
				if (!isValidBinaryInput())  {
					sendErrorMessage();
					return;
				}
				sendOutput(true);
			} else {
				if (!isValidDecimalInput()) {
					sendErrorMessage();
					return;
				}
				sendOutput(false);
			}
		});
		buttonHelp.addActionListener(e -> {
			showHelp();
		});
	}
	
	private boolean isValidDecimalInput() {
		String[] splitter = textfieldAddress.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (!(splitter[i].length() > 0 && splitter[i].length() < 4)) 
				return false;
		}
		return true;
	}
	
	private boolean isValidBinaryInput() {
		String[] splitter = textfieldAddress.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (splitter[i].length() != 8)
				return false;
		}
		return true;
	}
	
	private boolean isBinaryInput() {
		String[] splitter = textfieldAddress.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (splitter[i].length() != 8)
				return false;
		}
		return true;
	}
	
	private void sendOutput(boolean isBinary) {
		if (isBinary) {
			labelAddressResult.setText(" " + languageHandler.getKey("converter_IPv4_label_Address") + ": " + NetworkConverter.binaryIPv4ToDecimal(textfieldAddress.getText()));
		} else {
			labelAddressResult.setText(" " + languageHandler.getKey("converter_IPv4_label_Address") + ": " + NetworkConverter.decimalIPv4ToBinary(textfieldAddress.getText()));
		}
	}
	
	private boolean isValidInput() {
		String[] splitter = textfieldAddress.getText().split("\\.");
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
	
	private void showHelp() {
		Popup.showHelpMessage(languageHandler.getKey("converter_IPv4_help_WhatIsIt"), 
				languageHandler.getKey("converter_IPv4_help_HowDoesItWork"),
				languageHandler.getKey("converter_IPv4_help_Example"));
	}
}