package com.engine.utils;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelDetailedInformation extends JPanel {

	private static final long serialVersionUID = 7667181411454002324L;
	
	private String title;
	private String[] messages;
	private JLabel[] labels;
	private JLabel labelTitle;
	private JPanel[] panels;
	
	public PanelDetailedInformation(String title, String... messages) {
		this.title = title;
		this.messages = messages;
		initComponents();
		layoutComponents();
	}

	private void initComponents() {
		labels = new JLabel[messages.length];
		labelTitle = new JLabel("<html><b>" + toUpperCaseFirstChar(title), SwingConstants.HORIZONTAL);
		for (int i = 0; i < messages.length; i++) {
			labels[i] = new JLabel(toUpperCaseFirstChar(messages[i]), SwingConstants.HORIZONTAL);
		}
		if (labels.length % 2 != 0)
			panels = new JPanel[(int) (labels.length / 2) + 1];
		else
			panels = new JPanel[labels.length/2];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel(new BorderLayout());
		}
	}

	private void layoutComponents() {
		setLayout(new BorderLayout());
		panels[0].add(labelTitle, BorderLayout.NORTH);
		panels[0].add(labels[0], BorderLayout.CENTER);
		int index = 1;
		if (labels.length == 2) {
			panels[0].add(labels[1], BorderLayout.SOUTH);
		} else {
			for (int i = 1; i < panels.length; i++) {
				panels[i].add(labels[index], BorderLayout.NORTH);
				panels[i].add(labels[index+1], BorderLayout.CENTER);
				if (i == panels.length-1 && index+2 < labels.length) {
					panels[i].add(labels[index+2], BorderLayout.SOUTH);
				}
				index += 2;
			}
		}
		
		for (int i = 0; i < panels.length-1; i++) {
				panels[i].add(panels[i+1], BorderLayout.SOUTH);
		}
		add(panels[0]);
	}

	private String toUpperCaseFirstChar(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

}
