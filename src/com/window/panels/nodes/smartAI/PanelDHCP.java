package com.window.panels.nodes.smartAI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.components.Console;
import com.engine.components.TextArea;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.handlers.ValidatorHandler;
import com.engine.smartAI.SmartAIDHCP;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelDHCP extends PanelProtocol {

	private static final long serialVersionUID = -6059841989678237769L;

	private JLabel labelPoolName;
	private JLabel labelNetwork;
	private JLabel labelExcludedIPs;
	private JLabel labelDNSServer;
	private JLabel labelDomainName;
	
	private TextField txtPoolName;
	private TextField txtNetwork;
	private TextArea txtExcludedIPs;
	private TextField txtDNSServer;
	private TextField txtDomainName;
	
	private JButton btnCreate;
	
	private Console console;
	private SmartAIDHCP smartAI;
	
	private String strExample;
	
	public PanelDHCP(LanguageHandler languageHandler) {
		super(languageHandler, "DHCP");
	}

	@Override
	protected void initComponents() {
		labelPoolName = new JLabel(" " + languageHandler.getKey("smartAI_dhcp_poolName") +":* ");
		labelNetwork = new JLabel(" " + languageHandler.getKey("smartAI_dhcp_network") + ":* ");
		labelExcludedIPs = new JLabel(" " + languageHandler.getKey("smartAI_dhcp_excludedIPs") + ": ");
		labelDNSServer = new JLabel(" " + languageHandler.getKey("smartAI_dhcp_dnsServer") + ": ");
		labelDomainName = new JLabel(" " + languageHandler.getKey("smartAI_dhcp_domainName") + ": ");
		
		strExample = languageHandler.getKey("word_example(short)");
		
		txtPoolName = new TextField(strExample + ": Clients");
		txtNetwork = new TextField(strExample + ": 192.168.0.1/24");
		txtExcludedIPs = new TextArea(strExample + ": 192.168.0.0 - 192.168.0.1, 192.168.0.255");
		txtDNSServer = new TextField(strExample + ": 8.8.8.8");
		txtDomainName = new TextField(strExample + ": Corporation.com");
		
		btnCreate = new JButton(languageHandler.getKey("smartAI_button_createCommandList"));
		console = new Console();
		smartAI = new SmartAIDHCP(console);
	}

	@Override
	protected void layoutComponents() {
		JPanel components = new JPanel(new BorderLayout());
		
		JPanel panelDetails = new JPanel(new BorderLayout());
		
		JPanel panelDetailsNorth = new JPanel(new GridLayout(2, 2));
		setEmptyFieldSet(panelDetails);		
		JPanel panelDetailsPool = new JPanel(new BorderLayout());
		panelDetailsPool.add(labelPoolName, BorderLayout.WEST);
		panelDetailsPool.add(txtPoolName, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsPool);
		JPanel panelDetailsNetwork = new JPanel(new BorderLayout());
		panelDetailsNetwork.add(labelNetwork, BorderLayout.WEST);
		panelDetailsNetwork.add(txtNetwork, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsNetwork);
		JPanel panelDetailsDNSServer = new JPanel(new BorderLayout());
		panelDetailsDNSServer.add(labelDNSServer, BorderLayout.WEST);
		panelDetailsDNSServer.add(txtDNSServer, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsDNSServer);
		JPanel panelDetailsDomainName = new JPanel(new BorderLayout());
		panelDetailsDomainName.add(labelDomainName, BorderLayout.WEST);
		panelDetailsDomainName.add(txtDomainName, BorderLayout.CENTER);
		panelDetailsNorth.add(panelDetailsDomainName);
		
		JPanel panelDetailsSouth = new JPanel(new BorderLayout());
		panelDetailsSouth.add(labelExcludedIPs, BorderLayout.WEST);
		panelDetailsSouth.add(txtExcludedIPs, BorderLayout.CENTER);
		
		panelDetails.add(panelDetailsNorth, BorderLayout.NORTH);
		panelDetails.add(panelDetailsSouth, BorderLayout.CENTER);
		panelDetails.add(btnCreate, BorderLayout.SOUTH);
		
		JPanel panelOutput = new JPanel(new BorderLayout());
		setEmptyFieldSet(panelOutput);
		panelOutput.add(console);
		
		components.add(setTitle("DHCP"), BorderLayout.NORTH);
		components.add(panelDetails, BorderLayout.CENTER);
		
		add(components, BorderLayout.NORTH);
		add(panelOutput, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {
		btnCreate.addActionListener(e -> {
			if (isFieldsEmpty()) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_emptyfields"));
				return;
			}
			else if (!ValidatorHandler.isValidMajorNetwork(txtNetwork.getText())) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetwork"));
				return;
			}
			if (!txtDNSServer.isEmpty()) {
				if (!ValidatorHandler.isValidIPv4Address(txtDNSServer.getText())) {
					Popup.showErrorMessage(languageHandler.getKey("smartAI_dhcp_invalidDNSServer"));
					return;
				}
			}
			smartAI.clearConsole();
			smartAI.exec(txtPoolName.getText(), 
						 txtNetwork.getText(), 
						 txtDNSServer, 
						 txtExcludedIPs,
						 txtDomainName);
		});
	}
	
	private boolean isFieldsEmpty() {
		return txtPoolName.isEmpty() || txtNetwork.isEmpty();
	}
	
}
