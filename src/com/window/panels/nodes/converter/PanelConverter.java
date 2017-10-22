package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.Logger;
import com.utils.NetworkConverter;
import com.window.panels.PanelProtocol;

public class PanelConverter extends PanelProtocol {

	private static final long serialVersionUID = -5111118154502890039L;
	
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
		setLayout(new BorderLayout());
		/* adress */
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
		
		/* assembly */
		JPanel sub1 = new JPanel(new BorderLayout());
		sub1.add(panelAddress, BorderLayout.NORTH);
		sub1.add(new PanelConverterNetmask(), BorderLayout.CENTER);
		sub1.add(new PanelConverterRequestedHosts(), BorderLayout.SOUTH);
		
		JPanel sub2 = new JPanel(new BorderLayout());
		sub2.add(sub1, BorderLayout.NORTH);
		sub2.add(new JLabel(" "), BorderLayout.CENTER);
		
		add(setTitle("Converter"), BorderLayout.NORTH);
		add(sub2, BorderLayout.CENTER);
	}
	
	@Override
	protected void initListeners() {
		buttonAddress.addActionListener(e -> {
			String inputField = textfieldAddress.getText();
			int length = inputField.split("\\.")[0].length();
			int MaxLength = inputField.split("\\.").length;
			if (MaxLength != 4) {
				System.out.println("Address is not complete.");
				return;
			} else if (length == 8) {
				labelAddressResult.setText(" Address: " + NetworkConverter.binaryIPv4ToDecimal(inputField));
				sendIPv4ConvertionMessage(inputField, NetworkConverter.binaryIPv4ToDecimal(inputField));
			} else if (length <= 3) {
				labelAddressResult.setText(" Address: " + NetworkConverter.decimalIPv4ToBinary(inputField));
				sendIPv4ConvertionMessage(inputField, NetworkConverter.decimalIPv4ToBinary(inputField));
			}
		});
		
		
	}

	private void sendIPv4ConvertionMessage(String netInput, String output) {
		Logger.log("Convert IPv4: " + netInput + " {\n"
				+ "   " + String.format("%s %s %n", "IPv4:", output)
					+ "}");
	}
}