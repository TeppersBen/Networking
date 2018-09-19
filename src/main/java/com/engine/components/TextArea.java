package com.engine.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class TextArea extends JTextArea {

	private static final long serialVersionUID = 6715744721635848701L;

	private String filterName;
	
	public TextArea() {
		this("");
	}
	
	public TextArea(String filter) {
		filterName = " " + filter;
		createFieldFocusUtility();
		setFont(new Font("Tahoma", 0, 11));
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setWrapStyleWord(true);
		setLineWrap(true);
	}
	
	public boolean isEmpty() {
		if (getText().equalsIgnoreCase(filterName))
			return true;
		if (getText().equalsIgnoreCase(""))
			return true;
		return false;
	}
	
	private void createFieldFocusUtility() {
		setForeground(Color.GRAY);
		setText(filterName);
		addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (getText().equals(filterName)) {
		        	setText("");
		        	setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (getText().isEmpty()) {
		        	setForeground(Color.GRAY);
		        	setText(filterName);
		        }
		    }
		});
	}
	
}