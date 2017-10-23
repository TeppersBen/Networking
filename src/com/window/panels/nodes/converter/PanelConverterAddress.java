package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.NetworkConverter;
import com.utils.OptionPane;
import com.window.panels.PanelProtocol;

public class PanelConverterAddress extends PanelProtocol {

	private static final long serialVersionUID = 1L;
	private JLabel labelAddress;
	private JLabel labelAddressResult;
	private JTextField textfieldAddress;
	private JButton buttonAddress;
	
	@Override
	protected void initComponents() {
		labelAddress = new JLabel(" IPv4 Address: ");
		labelAddressResult = new JLabel(" Address: ");
		textfieldAddress = new JTextField(11);
		buttonAddress = new JButton("Convert Address");
	}

	@Override
	protected void layoutComponents() {
		JPanel panelAddress = new JPanel(new BorderLayout());
		JPanel panelAddressSub = new JPanel(new BorderLayout());
		JPanel panelAddressResult = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelAddress);
		panelAddressSub.add(labelAddress, BorderLayout.WEST);
		panelAddressSub.add(textfieldAddress, BorderLayout.CENTER);
		panelAddressResult.add(labelAddressResult);
		panelAddress.add(panelAddressSub, BorderLayout.NORTH);
		panelAddress.add(panelAddressResult, BorderLayout.CENTER);
		panelAddress.add(buttonAddress, BorderLayout.SOUTH);
		add(panelAddress);
	}

	private void sendErrorMessage() {
		OptionPane.showErrorMessage("Invalid IPv4 Address Detected!<br>" 
				+ "Example: (decimal) 192.168.0.0<br>" 
				+ "Example: (binary) 11000000.10101000.00000000.00000000");
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
			labelAddressResult.setText(" Address: " + NetworkConverter.binaryIPv4ToDecimal(textfieldAddress.getText()));
		} else {
			labelAddressResult.setText(" Address: " + NetworkConverter.decimalIPv4ToBinary(textfieldAddress.getText()));
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
}