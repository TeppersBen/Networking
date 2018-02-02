package com.engine.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextField extends JTextField {

	private static final long serialVersionUID = 1691849948846200668L;

	private String filterName;
	
	public TextField(String filterName) {
		this(2, filterName);
	}
	
	public TextField(int size, String filterName) {
		super(size);
		this.filterName = filterName;
		createFieldFocusUtility();
	}
	
	public TextField(int size) {
		super(size);
		this.filterName = "";
		createFieldFocusUtility();
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