package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.Formatter;
import com.utils.NetworkConverter;
import com.utils.OptionPane;
import com.window.panels.PanelProtocol;

public class PanelConverterNetmask extends PanelProtocol {

	private static final long serialVersionUID = 8395957340899010044L;
	
	private JLabel labelNetmask;
	private JLabel labelNetmaskResult;
	private JLabel labelNetmaskClass;
	private JLabel labelNetmaskTotalHosts;
	private JLabel labelNetmaskTotalSubnets;
	private JTextField textfieldNetmask;
	private JButton buttonNetmask;
	
	@Override
	protected void initComponents() {
		labelNetmask = new JLabel(" Netmask/CIDR: ");
		labelNetmaskResult = new JLabel(" Netmask|CIDR: ");
		labelNetmaskClass = new JLabel(" Class: ");
		labelNetmaskTotalSubnets = new JLabel(" Subnets: ");
		labelNetmaskTotalHosts = new JLabel(" Hosts: ");
		textfieldNetmask = new JTextField(11);
		buttonNetmask = new JButton("Convert Netmask");
	}
	
	@Override
	protected void layoutComponents() {
		JPanel panelNetmask = new JPanel(new BorderLayout());
		JPanel panelNetmaskSub = new JPanel(new BorderLayout());
		JPanel panelNetmaskResult = new JPanel(new BorderLayout());
		JPanel panelNetmaskResult1 = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelNetmask);
		panelNetmaskSub.add(labelNetmask, BorderLayout.WEST);
		panelNetmaskSub.add(textfieldNetmask, BorderLayout.CENTER);
		panelNetmaskResult.add(labelNetmaskResult, BorderLayout.NORTH);
		panelNetmaskResult.add(labelNetmaskClass, BorderLayout.CENTER);
		panelNetmaskResult.add(labelNetmaskTotalSubnets, BorderLayout.SOUTH);
		panelNetmaskResult1.add(labelNetmaskTotalHosts, BorderLayout.NORTH);
		panelNetmaskResult1.add(buttonNetmask, BorderLayout.CENTER);
		panelNetmask.add(panelNetmaskSub, BorderLayout.NORTH);
		panelNetmask.add(panelNetmaskResult, BorderLayout.CENTER);
		panelNetmask.add(panelNetmaskResult1, BorderLayout.SOUTH);
		add(panelNetmask);
	}
	
	@Override
	protected void initListeners() {
		buttonNetmask.addActionListener(e -> {
			if (isCIDRNotation()) {
				if (!isValidCIDRNotation()) {
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
				if (!isValidDecimalInput()) {
					sendErrorMessage();
					return;
				}
				sendOutput(false, false);
			}
		});
	}
	private boolean isCIDRNotation() {
		if (textfieldNetmask.getText().split("\\.").length != 1)
			return false;
		return true;
	}
	
	private boolean isValidCIDRNotation() {
		try {
			int cidr = Integer.parseInt(textfieldNetmask.getText());
			if (!(cidr >= 0 && cidr <= 32))
				return false;
		} catch (NumberFormatException ex) {
			return false;
		}
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
	
	private boolean isValidDecimalInput() {
		String[] splitter = textfieldNetmask.getText().split("\\.");
		if (splitter.length != 4)
			return false;
		for (int i = 0; i < 4; i++) {
			try {
				Integer.parseInt(splitter[i]);
			} catch (NumberFormatException ex) {
				return false;
			}
			if (!(splitter[i].length() >= 1 && splitter[i].length() <= 3))
				return false;
		}
		return true;
	}
	
	private void sendErrorMessage() {
		OptionPane.showErrorMessage("Invalid Netmask Detected!<br>" 
				+ "Example: (decimal) 255.255.0.0<br>" 
				+ "Example: (binary) 11111111.11111111.00000000.00000000<br>"
				+ "Example: (CIDR) 16 [range: (0 - 32)]");
	}
	
	private void sendOutput(boolean isCIDR, boolean isBinary) {
		if (isCIDR) {
			int netmask = Integer.parseInt(textfieldNetmask.getText());
			labelNetmaskResult.setText(" Netmask: " + NetworkConverter.netmaskIntegerToIP(netmask));
			labelNetmaskClass.setText(" Class: " + NetworkConverter.getNetmaskClass(NetworkConverter.netmaskIntegerToIP(netmask)));
			labelNetmaskTotalSubnets.setText(" Subnets: " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(netmask)));
			labelNetmaskTotalHosts.setText(" Hosts: " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(netmask)));
		} else {
			if (isBinary) {
				String netmask = NetworkConverter.binaryIPv4ToDecimal(textfieldNetmask.getText());
				if (!NetworkConverter.isValidNetmask(netmask)) {
					OptionPane.showErrorMessage(netmask + " is not a valid netmask!");
					return;
				}
				labelNetmaskResult.setText("<html>&#160;Netmask: " + NetworkConverter.netmaskIntegerToIP(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask))) + "<br>"
											+ "&#160;CIDR: " + NetworkConverter.netmaskDecimalToCIDR(netmask) + "</html>");
				labelNetmaskClass.setText(" Class: " + NetworkConverter.getNetmaskClass(netmask));
				labelNetmaskTotalSubnets.setText(" Subnets: " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskTotalHosts.setText(" Hosts: " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
			} else {
				String netmask = textfieldNetmask.getText();
				if (!NetworkConverter.isValidNetmask(netmask)) {
					OptionPane.showErrorMessage(netmask + " is not a valid netmask!");
					return;
				}
				labelNetmaskResult.setText(" CIDR: " + NetworkConverter.netmaskDecimalToCIDR(netmask));
				labelNetmaskClass.setText(" Class: " + NetworkConverter.getNetmaskClass(netmask));
				labelNetmaskTotalSubnets.setText(" Subnets: " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
				labelNetmaskTotalHosts.setText(" Hosts: " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(netmask)))));
			}
		}
	}
}