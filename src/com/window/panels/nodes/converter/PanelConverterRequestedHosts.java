package com.window.panels.nodes.converter;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.utils.NetworkConverter;
import com.utils.OptionPane;
import com.window.panels.PanelProtocol;
import com.window.panels.nodes.vlsm.VLSMSpecializedCalculator;

public class PanelConverterRequestedHosts extends PanelProtocol {

	private static final long serialVersionUID = 2371930704559590937L;

	private JLabel labelHosts;
	private JLabel labelHostsResultCIDR;
	private JLabel labelHostsResultNetmask;
	private JLabel labelHostsClass;
	private JLabel labelHostsTotalHosts;
	private JLabel labelHostsTotalSubnets;
	private JTextField textfieldHosts;
	private JButton buttonHosts;
	
	@Override
	protected void initComponents() {
		labelHosts = new JLabel(" Requested hosts: ");
		labelHostsResultCIDR = new JLabel(" CIDR: ");
		labelHostsResultNetmask = new JLabel(" Netmask: ");
		labelHostsClass = new JLabel(" Class: ");
		labelHostsTotalSubnets = new JLabel(" Subnets: ");
		labelHostsTotalHosts = new JLabel(" Hosts: ");
		textfieldHosts = new JTextField(11);
		buttonHosts = new JButton("Convert hosts");
	}

	@Override
	protected void layoutComponents() {
		JPanel panelHosts = new JPanel(new BorderLayout());
		JPanel panelHostsSub = new JPanel(new BorderLayout());
		JPanel panelHostsResult = new JPanel(new BorderLayout());
		JPanel panelHostsResult1 = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelHosts);
		panelHostsSub.add(labelHosts, BorderLayout.WEST);
		panelHostsSub.add(textfieldHosts, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsResultCIDR, BorderLayout.NORTH);
		panelHostsResult.add(labelHostsResultNetmask, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsClass, BorderLayout.SOUTH);
		panelHostsResult1.add(labelHostsTotalSubnets, BorderLayout.NORTH);
		panelHostsResult1.add(labelHostsTotalHosts, BorderLayout.CENTER);
		panelHostsResult1.add(buttonHosts, BorderLayout.SOUTH);
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
			labelHostsTotalHosts.setText(" Hosts: " + VLSMSpecializedCalculator.getValidHost(value));
			labelHostsResultCIDR.setText(" CIDR: " + VLSMSpecializedCalculator.getCIDR(value));
			labelHostsResultNetmask.setText(" Netmask: " + VLSMSpecializedCalculator.getNetmask(value));
			labelHostsClass.setText(" Class: " + NetworkConverter.getNetmaskClass(VLSMSpecializedCalculator.getNetmask(value)));
			labelHostsTotalSubnets.setText(" Subnets: " + NetworkConverter.getTotalValidSubnets(Integer.parseInt(VLSMSpecializedCalculator.getCIDR(value))));
		});
	}
	
	private boolean isValidInput() {
		int value = 0;
		try {
			value = Integer.parseInt(textfieldHosts.getText());
		} catch (NumberFormatException ex) {
			OptionPane.showErrorMessage("Invalid Host Request Input");
			return false;
		}
		if (value < 0) {
			OptionPane.showErrorMessage("Invalid Host Request Input");
			return false;
		}
		return true;
	}

}