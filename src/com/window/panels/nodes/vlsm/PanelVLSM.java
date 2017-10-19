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

import com.window.panels.PanelProtocol;

public class PanelVLSM extends PanelProtocol {

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
	
	private VLSMConverter converter;

	@Override
	protected void initComponents() {
		labelMajorNetwork = new JLabel(" Major network ");
		textMajorNetwork = new JTextField(5);

		labelSubnets = new JLabel(" Subnets ");
		labelNumberofSubnets = new JLabel(" Number of subnets ");
		textNumberofSubnets = new JTextField(5);
		buttonChangeNumberofSubnets = new JButton("Change");

		panelSubnetTable = new SubnetPanelCreator(5);
		textNumberofSubnets.setText("5");

		buttonSubmit = new JButton("Submit");
		
		scroll = new JScrollPane();
		
		converter = new VLSMConverter();
	}

	@Override
	protected void layoutComponents() {
		add(setTitle("VLSM"), BorderLayout.NORTH);

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
				subnets = (subnets >= 20) ? 20 : (subnets < 0) ? 0 : subnets;
				panelSubnetTable.updateTable(subnets);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		});
		
		buttonSubmit.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				converter.dispatchEvent(new WindowEvent(converter, WindowEvent.WINDOW_CLOSING));
				converter.build(panelSubnetTable.getData());
			});
		});
	}

}

class SubnetPanelCreator extends JPanel {

	private static final long serialVersionUID = -6000195008416312570L;

	private int character;
	private JTextField[][] data;
	
	public SubnetPanelCreator(int subnets) {
		build(subnets);
	}
	
	public void build(int subnets) {
		setLayout(new GridLayout(subnets+1, 2));
		character = (int) new Character('A');
		add(new JLabel("Name", SwingConstants.CENTER));
		add(new JLabel("Size", SwingConstants.CENTER));
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
	
	public void updateTable(int subnets) {
		removeAll();
		build(subnets);
		repaint();
		revalidate();
	}
	
	public JTextField[][] getData() {
		return data;
	}
}