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
import com.utils.calculators.VLSMSpecializedCalculator;
import com.window.panels.PanelProtocol;

public class PanelConverterRequestedHosts extends PanelProtocol {

	public PanelConverterRequestedHosts(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = 2371930704559590937L;

	private JLabel labelHosts;
	private JLabel labelHostsResultCIDR;
	private JLabel labelHostsResultNetmask;
	private JLabel labelHostsClass;
	private JLabel labelHostsTotalHosts;
	private JLabel labelHostsTotalSubnets;
	private JTextField textfieldHosts;
	private JButton buttonHosts;
	private JButton buttonHelp;
	
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
		buttonHelp = new JButton("?");
	}

	@Override
	protected void layoutComponents() {
		JPanel panelHosts = new JPanel(new BorderLayout());
		JPanel panelHostsSub = new JPanel(new BorderLayout());
		JPanel panelHostsResult = new JPanel(new BorderLayout());
		JPanel panelHostsResult1 = new JPanel(new BorderLayout());
		JPanel panelButtons = new JPanel(new BorderLayout());
		panelButtons.add(buttonHosts, BorderLayout.CENTER);
		panelButtons.add(buttonHelp, BorderLayout.EAST);
		setEmptyFieldSet(panelHosts);
		panelHostsSub.add(labelHosts, BorderLayout.WEST);
		panelHostsSub.add(textfieldHosts, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsResultCIDR, BorderLayout.NORTH);
		panelHostsResult.add(labelHostsResultNetmask, BorderLayout.CENTER);
		panelHostsResult.add(labelHostsClass, BorderLayout.SOUTH);
		panelHostsResult1.add(labelHostsTotalSubnets, BorderLayout.NORTH);
		panelHostsResult1.add(labelHostsTotalHosts, BorderLayout.CENTER);
		panelHostsResult1.add(panelButtons, BorderLayout.SOUTH);
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
		buttonHelp.addActionListener(e -> {
			if (Settings.debug)
				showHelp();
			else
				Popup.showMaintenanceMessage();
		});
	}
	
	private boolean isValidInput() {
		int value = 0;
		try {
			value = Integer.parseInt(textfieldHosts.getText());
		} catch (NumberFormatException ex) {
			Popup.showErrorMessage("Invalid Host Request Input");
			return false;
		}
		if (value < 0) {
			Popup.showErrorMessage("Invalid Host Request Input");
			return false;
		}
		return true;
	}

	private void showHelp() {
		final String SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;";
		String whatIsIt = "Each subnet has a limited amount of usable hosts.<br>"
				+ "This converter will help you figure out what netmask you should<br>"
				+ "use for your requested host size.";
		String howDoesItWork = SPACE + ">>-> Integer value -> [CIDR;Netmask(decimal);Class;Subnets;UsableHosts]";
		String example = SPACE + ">>-> Input: 50<br>"
				+ SPACE + ">>-> Output: [CIDR: 26; Netmask(decimal): 255.255.255.192; Class: C; Subnets: 4; UsableHosts: 62]";
		Popup.showHelpMessage(whatIsIt, howDoesItWork, example);
	}
}