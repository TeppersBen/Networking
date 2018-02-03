package com.window.panels.nodes.smartAI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.engine.components.Console;
import com.engine.components.TextField;
import com.engine.handlers.LanguageHandler;
import com.engine.handlers.ValidatorHandler;
import com.engine.smartAI.SmartAIsACL;
import com.engine.utils.Popup;
import com.window.panels.PanelProtocol;

public class PanelsACL extends PanelProtocol {

	private static final long serialVersionUID = 7653653697994367242L;

	private JLabel labelListName;
	private JLabel labelFirstIP;
	private JLabel labelLastIP;
	private JLabel labelInterfaceName;
	
	private TextField txtListName;
	private TextField txtFirstIP;
	private TextField txtLastIP;
	private TextField txtInterfaceName;
	
	private JButton btnCreateCommandList;
	private Console console;
	private SmartAIsACL sACL;
	
	private String strExample;
	
	public PanelsACL(LanguageHandler languageHandler) {
		super(languageHandler, "sACL");
	}

	@Override
	protected void initComponents() {
		labelListName = new JLabel(" " + languageHandler.getKey("smartAI_sacl_listName") + ":* ");
		labelFirstIP = new JLabel(" " + languageHandler.getKey("converter_acl_firstip") + ":* ");
		labelLastIP = new JLabel(" " + languageHandler.getKey("converter_acl_lastip") + ":* ");
		labelInterfaceName = new JLabel(" " + languageHandler.getKey("smartAI_sacl_interfaceName") + ":* ");
		
		strExample = languageHandler.getKey("word_example(short)");
		
		txtListName = new TextField(strExample + ": charlie");
		txtFirstIP = new TextField(strExample + ": 192.168.0.5");
		txtLastIP = new TextField(strExample + ": 192.168.0.100");
		txtInterfaceName = new TextField(strExample + ": f0/1");
		
		btnCreateCommandList = new JButton(languageHandler.getKey("smartAI_button_createCommandList"));
		console = new Console();
		sACL = new SmartAIsACL(console);
	}

	@Override
	protected void layoutComponents() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel components = new JPanel(new BorderLayout());
		
		JPanel details = new JPanel(new GridLayout(2, 2));
		JPanel detailsName = new JPanel(new BorderLayout());
		detailsName.add(labelListName, BorderLayout.WEST);
		detailsName.add(txtListName, BorderLayout.CENTER);
		details.add(detailsName);
		JPanel detailsInterfaceName = new JPanel(new BorderLayout());
		detailsInterfaceName.add(labelInterfaceName, BorderLayout.WEST);
		detailsInterfaceName.add(txtInterfaceName, BorderLayout.CENTER);
		details.add(detailsInterfaceName);
		JPanel detailsFirst = new JPanel(new BorderLayout());
		detailsFirst.add(labelFirstIP, BorderLayout.WEST);
		detailsFirst.add(txtFirstIP, BorderLayout.CENTER);
		details.add(detailsFirst);
		JPanel detailsLast = new JPanel(new BorderLayout());
		detailsLast.add(labelLastIP, BorderLayout.WEST);
		detailsLast.add(txtLastIP, BorderLayout.CENTER);
		details.add(detailsLast);
		
		panel.add(details, BorderLayout.CENTER);
		panel.add(btnCreateCommandList, BorderLayout.SOUTH);
		
		components.add(setTitle("sACL"), BorderLayout.NORTH);
		components.add(panel, BorderLayout.CENTER);
		
		add(components, BorderLayout.NORTH);
		add(console, BorderLayout.CENTER);
		
		setEmptyFieldSet(panel);
	}

	@Override
	protected void initListeners() {
		btnCreateCommandList.addActionListener(e -> {
			if (isEmpty()) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_emptyfields"));
				return;
			}
			else if (!ValidatorHandler.isValidIPv4Address(txtFirstIP.getText())
					|| !ValidatorHandler.isValidIPv4Address(txtLastIP.getText())) {
				Popup.showErrorMessage(languageHandler.getKey("converter_IPv4_error_invalidIPv4Address"));
				return;
			}
			sACL.clearConsole();
			sACL.exec(txtListName.getText(), 
					  txtFirstIP.getText(),
					  txtLastIP.getText(), 
					  txtInterfaceName.getText());
		});
	}
	
	private boolean isEmpty() {
		return txtListName.isEmpty() || txtFirstIP.isEmpty() || txtLastIP.isEmpty() || txtInterfaceName.isEmpty();
	}

}
