package com.window.panels.nodes;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.Formatter;
import com.utils.Logger;
import com.utils.NetworkConverter;
import com.window.panels.PanelProtocol;

public class PanelConverter extends PanelProtocol {

	private static final long serialVersionUID = -5111118154502890039L;
	
	private JLabel labelAddress;
	private JLabel labelAddressResult;
	private JTextField textfieldAddress;
	private JButton buttonAddress;
	
	private JLabel labelNetmask;
	private JLabel labelNetmaskResult;
	private JLabel labelNetmaskClass;
	private JLabel labelNetmaskTotalHosts;
	private JLabel labelNetmaskTotalSubnets;
	private JTextField textfieldNetmask;
	private JButton buttonNetmask;
	
	@Override
	protected void initComponents() {
		labelAddress = new JLabel(" IPv4 Address: ");
		labelAddressResult = new JLabel(" Address: ");
		textfieldAddress = new JTextField(11);
		buttonAddress = new JButton("Convert Address");
		
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
		
		/* netmask */
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
		
		/* assembly */
		JPanel sub1 = new JPanel(new BorderLayout());
		sub1.add(panelAddress, BorderLayout.NORTH);
		sub1.add(panelNetmask, BorderLayout.CENTER);
		
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
		
		buttonNetmask.addActionListener(e -> {
			String inputField = textfieldNetmask.getText();
			int length = inputField.split("\\.")[0].length();
			int MaxLength = inputField.split("\\.").length;
			int netmask = 0;
			if (!inputField.isEmpty())
				netmask = Integer.parseInt(inputField.split("\\.")[0]);
			if (inputField.length() > 35) {
				System.out.println("Netmask is too long.");
			} else if (length == 3) {
				if (MaxLength != 4) {
					System.out.println("Netmask is not complete.");
					return;
				}
				netmask = Integer.parseInt(NetworkConverter.netmaskDecimalToCIDR(inputField));
				labelNetmaskResult.setText(" CIDR: " + netmask);
				labelNetmaskClass.setText(" Class: " + NetworkConverter.getNetmaskClass(NetworkConverter.netmaskIntegerToIP(netmask)));
				labelNetmaskTotalSubnets.setText(" Subnets: " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(netmask)));
				labelNetmaskTotalHosts.setText(" Hosts: " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(netmask)));
				sendNetmaskConvertionMessage(inputField, netmask, NetworkConverter.getNetmaskClass(NetworkConverter.netmaskIntegerToIP(netmask)), NetworkConverter.getTotalValidSubnets(netmask), NetworkConverter.getTotalValidHosts(netmask));
			} else if (length == 8) {
				if (MaxLength != 4 || inputField.length() < 35) {
					System.out.println("Netmask is not complete.");
					return;
				}
				System.out.println("This is a binary input");
			} else if (netmask > 0 && netmask <= 32) {
				labelNetmaskResult.setText(" Netmask: " + NetworkConverter.netmaskIntegerToIP(netmask));
				labelNetmaskClass.setText(" Class: " + NetworkConverter.getNetmaskClass(NetworkConverter.netmaskIntegerToIP(netmask)));
				labelNetmaskTotalSubnets.setText(" Subnets: " + Formatter.formatInteger(NetworkConverter.getTotalValidSubnets(netmask)));
				labelNetmaskTotalHosts.setText(" Hosts: " + Formatter.formatInteger(NetworkConverter.getTotalValidHosts(netmask)));
				sendCIDRConvertionMessage(inputField, NetworkConverter.netmaskIntegerToIP(netmask), NetworkConverter.getNetmaskClass(NetworkConverter.netmaskIntegerToIP(netmask)), NetworkConverter.getTotalValidSubnets(netmask), NetworkConverter.getTotalValidHosts(netmask));
			} else {
				System.out.println("Netmask is not complete.");
			}
		});
	}
	
	private void sendNetmaskConvertionMessage(String netInput, int cidr, String netClass, int subnets, int hosts) {
		Logger.log("Convert Netmask: " + netInput + " {\n"
					+ "   " + String.format("%s %d %n", "CIDR:", cidr)
					+ "   " + String.format("%s %s %n", "Class:", netClass)
					+ "   " + String.format("%s %s %n", "Subnets:", Formatter.formatInteger(subnets))
					+ "   " + String.format("%s %s %n", "Hosts:", Formatter.formatInteger(hosts))
					+ "}");
	}
	
	private void sendCIDRConvertionMessage(String netInput, String netmask, String netClass, int subnets, int hosts) {
		Logger.log("Convert CIDR: " + netInput + " {\n"
					+ "   " + String.format("%s %s %n", "Netmask:", netmask)
					+ "   " + String.format("%s %s %n", "Class:", netClass)
					+ "   " + String.format("%s %s %n", "Subnets:", Formatter.formatInteger(subnets))
					+ "   " + String.format("%s %s %n", "Hosts:", Formatter.formatInteger(hosts))
					+ "}");
	}
	
	private void sendIPv4ConvertionMessage(String netInput, String output) {
		Logger.log("Convert IPv4: " + netInput + " {\n"
				+ "   " + String.format("%s %s %n", "IPv4:", output)
					+ "}");
	}
}