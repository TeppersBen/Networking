package com.window.panels.nodes.converter;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.engine.calculators.NetworkConverter;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelConverterACL extends PanelProtocol {

	private static final long serialVersionUID = -7835341280525800252L;

	private JLabel lblFirstIP;
	private TextField txtFirstIP;
	
	private JLabel lblLastIP;
	private TextField txtLastIP;
	
	private JLabel lblWildcard;
	
	private JButton btnCreateWildcard;
	private JButton btnHelp;
	
	public PanelConverterACL(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	@Override
	protected void initComponents() {
		lblFirstIP = new JLabel(" " + languageHandler.getKey("converter_acl_firstip") + ": ");
		lblLastIP = new JLabel(" :" + languageHandler.getKey("converter_acl_lastip") + " ");
		lblWildcard = new JLabel(" " + languageHandler.getKey("converter_acl_wildcard") + ": ");
		txtFirstIP = new TextField(languageHandler.getKey("word_example") + ": 10.0.1.16");
		txtLastIP = new TextField(languageHandler.getKey("word_example") + ": 10.0.2.120");
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
		JPanel northtxt = new JPanel(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 1.0;
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = 0;
		northtxt.add(txtFirstIP, constraint);
		northtxt.add(new JLabel(" - ", SwingConstants.HORIZONTAL));
		constraint.gridx = 2;
		northtxt.add(txtLastIP, constraint);
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