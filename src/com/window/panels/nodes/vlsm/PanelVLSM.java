package com.window.panels.nodes.vlsm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.handlers.LanguageHandler;
import com.utils.Popup;
import com.utils.calculators.VLSMSpecializedCalculator;
import com.window.panels.PanelProtocol;

public class PanelVLSM extends PanelProtocol {

	public PanelVLSM(LanguageHandler languageHandler) {
		super(languageHandler);
	}

	private static final long serialVersionUID = -4200773632900692796L;

	private JLabel labelMajorNetwork;
	private JTextField textMajorNetwork;

	private JLabel labelSubnets;
	private JLabel labelNumberofSubnets;
	private JTextField textNumberofSubnets;
	private JButton buttonChangeNumberofSubnets;
	private SubnetPanelCreator panelSubnetTable;

	private JButton buttonSubmit;
	
	private JScrollPane scroll;
	
	private PanelVLSMTable converter;

	@Override
	protected void initComponents() {
		setPanelName(languageHandler.getKey("tab_calculators_vlsm"));
		labelMajorNetwork = new JLabel(" " + languageHandler.getKey("vlsm_label_majornetwork") + " ");
		textMajorNetwork = new JTextField(5);

		labelSubnets = new JLabel(" " + languageHandler.getKey("vlsm_label_subnets") + " ");
		labelNumberofSubnets = new JLabel(" " + languageHandler.getKey("vlsm_label_numberofsubnets") + " ");
		textNumberofSubnets = new JTextField(5);
		buttonChangeNumberofSubnets = new JButton(languageHandler.getKey("vlsm_button_changesubnets"));

		panelSubnetTable = new SubnetPanelCreator(5, languageHandler);
		textNumberofSubnets.setText("5");

		buttonSubmit = new JButton(languageHandler.getKey("vlsm_button_submit"));
		
		scroll = new JScrollPane();
		
		converter = new PanelVLSMTable();
	}

	@Override
	protected void layoutComponents() {
		add(setTitle(getPanelName()), BorderLayout.NORTH);

		JPanel panelMajorNetwork = new JPanel(new BorderLayout());
		panelMajorNetwork.add(labelMajorNetwork, BorderLayout.WEST);
		panelMajorNetwork.add(textMajorNetwork, BorderLayout.CENTER);
		
		JPanel panelSubnetAmount = new JPanel(new BorderLayout());
		panelSubnetAmount.add(labelNumberofSubnets, BorderLayout.WEST);
		panelSubnetAmount.add(textNumberofSubnets, BorderLayout.CENTER);
		panelSubnetAmount.add(buttonChangeNumberofSubnets, BorderLayout.EAST);
		
		JPanel panelSubnets2 = new JPanel(new BorderLayout());
		panelSubnets2.add(panelSubnetAmount, BorderLayout.NORTH);
		
		JPanel panelSubnets3 = new JPanel(new BorderLayout());
		panelSubnets3.add(panelSubnetTable, BorderLayout.NORTH);
		panelSubnets3.add(panelSubnets2, BorderLayout.CENTER);
		
		JPanel panelSubnets = new JPanel(new BorderLayout());
		panelSubnets.add(labelSubnets, BorderLayout.WEST);
		panelSubnets.add(panelSubnets3, BorderLayout.CENTER);

		JPanel merge = new JPanel(new BorderLayout());
		merge.add(panelMajorNetwork, BorderLayout.NORTH);
		merge.add(panelSubnets, BorderLayout.CENTER);
		merge.add(buttonSubmit, BorderLayout.SOUTH);
		
		JPanel merge2 = new JPanel(new BorderLayout());
		merge2.add(merge, BorderLayout.NORTH);

		labelSubnets.setBorder(BorderFactory.createTitledBorder(""));
		
		setEmptyFieldSet(panelMajorNetwork);
		setEmptyFieldSet(panelSubnets);
		
		scroll.add(merge2);
		scroll.setViewportView(merge2);
		
		add(scroll, BorderLayout.CENTER);
	}

	@Override
	protected void initListeners() {
		buttonChangeNumberofSubnets.addActionListener(e -> {
			try {
				int subnets = Integer.parseInt(textNumberofSubnets.getText());
				if (subnets > 20) {
					Popup.showErrorMessage(languageHandler.getKey("vlsm_error_subnetslimited"));
				} else if (subnets < 1) {
					Popup.showErrorMessage(languageHandler.getKey("vlsm_error_subnetstoolow"));
				} else {
					panelSubnetTable.updateTable(subnets);
				}
			} catch (NumberFormatException ex) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidsubnets") + "<br>"
						+ languageHandler.getKey("vlsm_error_validsubnets"));
			}
		});
		
		buttonSubmit.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				if (!isReadyToCreateTable())	
					return;
				converter.dispatchEvent(new WindowEvent(converter, WindowEvent.WINDOW_CLOSING));
				converter = new PanelVLSMTable();
				converter.build(textMajorNetwork.getText(), panelSubnetTable.getData(), languageHandler);
			});
		});
	}
	
	private boolean isValidMajorNetwork() {
		String ip = textMajorNetwork.getText().split("/")[0];
		int ipValue = 0;
		if (textMajorNetwork.getText().split("/").length != 2) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetwork") + "<br>"
					+ languageHandler.getKey("word_example").substring(0, 1).toUpperCase() + languageHandler.getKey("word_example").substring(1) + ": 192.168.0.0/24");
			return false;
		}
		if (ip.split("\\.").length != 4) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetwork") + "<br>"
					+ languageHandler.getKey("word_example").substring(0, 1).toUpperCase() + languageHandler.getKey("word_example").substring(1) + ": 192.168.0.0/24");
			return false;
		}
		try {
			int CIDR = Integer.parseInt(textMajorNetwork.getText().split("/")[1]);
			if (CIDR < 0 || CIDR > 32) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidcidr"));
				return false;
			}
		} catch (NumberFormatException ex) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidcidrnotation") + "<br>"
					+ languageHandler.getKey("vlsm_error_validcidrnotation"));
			return false;
		}
		for (int i = 0; i < 4; i++) {
			ipValue = Integer.parseInt(ip.split("\\.")[i]);
			if (ipValue < 0 || ipValue > 255) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetworksegment") + "<br>" 
						+ languageHandler.getKey("vlsm_error_validmajornetworksegment"));
				return false;
			}
		}
		return true;
	}
	
	private boolean isPossibleToCreateVLSM() {
		int totalHosts = Integer.parseInt(textMajorNetwork.getText().split("/")[1]);
		totalHosts = VLSMSpecializedCalculator.getTotalValidHosts(totalHosts);
		if (panelSubnetTable.getTotalRequestedHostSize() > totalHosts) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_impossible") + "<br>"
					+ languageHandler.getKey("vlsm_error_impossible_available") + " " + totalHosts + " "
					+ languageHandler.getKey("word_hosts"));
			return false;
		}
		return true;
	}
	
	private boolean isReadyToCreateTable() {
		if (textMajorNetwork.getText().isEmpty() || !panelSubnetTable.isReadyToCreateTable()) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_emptyfields"));
			return false;
		}
		if (!panelSubnetTable.isSubnetSizesValid()) {
			return false;
		}
		if (!isValidMajorNetwork()) {
			return false;
		}
		if (!isPossibleToCreateVLSM())
			return false;
		return true;
	}
}

class SubnetPanelCreator extends JPanel {

	private static final long serialVersionUID = -6000195008416312570L;

	private int character;
	private JTextField[][] data;
	private LanguageHandler languageHandler;
	
	public SubnetPanelCreator(int subnets, LanguageHandler handler) {
		setLanguageHandler(handler);
		build(subnets);
	}
	
	public void build(int subnets) {
		setLayout(new GridLayout(subnets+1, 2));
		character = (int) 'A';
		add(new JLabel(languageHandler.getKey("vlsm_label_subnetname"), SwingConstants.CENTER));
		add(new JLabel(languageHandler.getKey("vlsm_label_subnetsize"), SwingConstants.CENTER));
		data = new JTextField[subnets][2];
		for (int i = 0; i < subnets; i++) {
			data[i][0] = new JTextField(1);
			data[i][0].setText(String.valueOf((char) character));
			character++;
			
			data[i][1] = new JTextField(1);
			
			add(data[i][0]);
			add(data[i][1]);
		}
	}
	
	public int getTotalRequestedHostSize() {
		int value = 0;
		for (int i = 0; i < data.length; i++) {
			value += Integer.parseInt(data[i][1].getText());
		}
		return value;
	}
	
	public boolean isSubnetSizesValid() {
		for (int i = 0; i < data.length; i++) {
			try {
				Integer.parseInt(data[i][1].getText());
			} catch (NumberFormatException ex) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidsubnetsizeinput"));
				return false;
			}
			
		}
		return true;
	}
	
	public boolean isReadyToCreateTable() {
		for (int i = 0; i < data.length; i++) {
			for (int x = 0; x < data[0].length; x++) {
				if (data[i][x].getText().isEmpty())
					return false;
			}
		}
		return true;
	}
	
	public void updateTable(int subnets) {
		removeAll();
		build(subnets);
		repaint();
		revalidate();
	}
	
	public JTextField[][] getData() {
		return data;
	}
	
	public void setLanguageHandler(LanguageHandler handler) {
		languageHandler = handler;
	}
}