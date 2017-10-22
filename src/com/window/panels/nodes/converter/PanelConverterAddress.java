package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.NetworkConverter;
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
			} else if (length <= 3) {
				labelAddressResult.setText(" Address: " + NetworkConverter.decimalIPv4ToBinary(inputField));
			}
		});
	}
	
}