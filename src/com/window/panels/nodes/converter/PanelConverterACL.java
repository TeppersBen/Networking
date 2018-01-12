package com.window.panels.nodes.converter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.engine.calculators.NetworkConverter;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelConverterACL extends PanelProtocol{

	private static final long serialVersionUID = -7835341280525800252L;

	private JLabel lblFirstIP;
	private JTextField txtFirstIP;
	
	private JLabel lblLastIP;
	private JTextField txtLastIP;
	
	private JLabel lblWildcard;
	
	private JButton btnCreateWildcard;
	private JButton btnHelp;
	
	public PanelConverterACL(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	@Override
	protected void initComponents() {
		lblFirstIP = new JLabel(" " + languageHandler.getKey("converter_acl_firstip") + ":");
		lblLastIP = new JLabel(" :" + languageHandler.getKey("converter_acl_lastip") + " ");
		lblWildcard = new JLabel(" " + languageHandler.getKey("converter_acl_wildcard") + ": ");
		txtFirstIP = new JTextField(15);
		txtLastIP = new JTextField(15);
		btnCreateWildcard = new JButton(languageHandler.getKey("converter_acl_button_createwildcard"));
		btnHelp = new JButton(languageHandler.getKey("converter_button_Help"));
	}

	@Override
	protected void layoutComponents() {
		setLayout(new BorderLayout());
		setEmptyFieldSet(this);
		JPanel buttons = new JPanel(new BorderLayout());
		buttons.add(btnCreateWildcard, BorderLayout.CENTER);
		buttons.add(btnHelp, BorderLayout.EAST);
		JPanel north = new JPanel(new BorderLayout());
		JPanel northtxt = new JPanel(new FlowLayout());
		northtxt.add(txtFirstIP);
		northtxt.add(new JLabel("-"));
		northtxt.add(txtLastIP);
		north.add(lblFirstIP, BorderLayout.WEST);
		north.add(northtxt, BorderLayout.CENTER);
		north.add(lblLastIP, BorderLayout.EAST);
		JPanel assemble = new JPanel(new BorderLayout());
		assemble.add(north, BorderLayout.NORTH);
		assemble.add(lblWildcard, BorderLayout.CENTER);
		assemble.add(buttons, BorderLayout.SOUTH);
		add(assemble, BorderLayout.NORTH);
	}

	@Override
	protected void initListeners() {
		btnCreateWildcard.addActionListener(e -> {
			if (!isValidIP()) {
				Popup.showErrorMessage(languageHandler.getKey("converter_acl_error_invalid_ip"));
				return;
			}
			setWildCard(NetworkConverter.getRequestedWildcard(txtFirstIP.getText(), txtLastIP.getText()));
		});
		btnHelp.addActionListener(e -> {
			Popup.showHelpMessage(
					languageHandler.getKey("converter_acl_help_WhatIsIt"), 
					languageHandler.getKey("converter_acl_help_HowDoesItWork"), 
					languageHandler.getKey("converter_acl_help_Example"));
		});
	}
	
	private boolean isValidIP() {
		String[] firstIP = txtFirstIP.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (!(firstIP[i].length() > 0 && firstIP[i].length() < 4)) 
				return false;
			if (Integer.parseInt(firstIP[i]) > 255)
				return false;
			if (Integer.parseInt(firstIP[i]) < 0)
				return false;
		}
		String[] lastIP = txtLastIP.getText().split("\\.");
		for (int i = 0; i < 4; i++) {
			if (!(lastIP[i].length() > 0 && lastIP[i].length() < 4)) 
				return false;
			if (Integer.parseInt(lastIP[i]) > 255)
				return false;
			if (Integer.parseInt(lastIP[i]) < 0)
				return false;
		}
		return true;
	}
	
	private void setWildCard(String wildcard) {
		lblWildcard.setText(" " + languageHandler.getKey("converter_acl_wildcard") + ": " + wildcard);
	}

}