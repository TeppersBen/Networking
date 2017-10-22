package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.Formatter;
import com.utils.NetworkConverter;
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
			} else {
				System.out.println("Netmask is not complete.");
			}
		});
	}
}