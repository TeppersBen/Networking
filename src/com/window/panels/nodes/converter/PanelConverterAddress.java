package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Settings;
import com.handlers.LanguageHandler;
import com.utils.Popup;
import com.utils.calculators.NetworkConverter;
import com.window.panels.PanelProtocol;

public class PanelConverterAddress extends PanelProtocol {

	public PanelConverterAddress(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 1L;
	private JLabel labelAddress;
	private JLabel labelAddressResult;
	private JTextField textfieldAddress;
	private JButton buttonAddress;
	private JButton buttonHelp;
	
	@Override
	protected void initComponents() {
		labelAddress = new JLabel(" IPv4 Address: ");
		labelAddressResult = new JLabel(" Address: ");
		textfieldAddress = new JTextField(11);
		buttonAddress = new JButton("Convert Address");
		buttonHelp = new JButton("?");
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
		Popup.showErrorMessage("Invalid IPv4 Address Detected!<br>" 
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
		buttonHelp.addActionListener(e -> {
			if (Settings.debug)
				showHelp();
			else
				Popup.showMaintenanceMessage();
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
	
	private void showHelp() {
		final String SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;";
		String whatIsIt = "Internet Protocol version 4 (IPv4) is the fourth version of the Internet Protocol (IP).<br>"
				+ "It is one of the core protocols of standards-based internetworking methods in the Internet.<br>" 
				+ "It still routes most Internet traffic today, despite the ongoing deployment of a successor protocol, IPv6.<br>"
				+ "IPv4 is a connectionless protocol for use on packet-switched networks";
		String howDoesItWork = "This converter allows you to use following convertions:<br>"
				+ SPACE + ">>-> Binary -> Decimal<br>"
				+ SPACE + ">>-> Decimal -> Binary";
		String example = "Decimal:<br>"
				+ SPACE + ">>-> Input: 192.168.0.1<br>"
				+ SPACE + ">>-> Output: 11000000.10101000.00000000.00000001<br>"
				+ "Binary:<br>"
				+ SPACE + ">>-> Input: 11000000.10101000.00001001.00000111<br>"
				+ SPACE + ">>-> Output: 192.168.5.7";
		Popup.showHelpMessage(whatIsIt, howDoesItWork, example);
	}
}