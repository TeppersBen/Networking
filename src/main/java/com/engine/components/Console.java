package com.engine.components;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JScrollPane {

	private static final long serialVersionUID = 8151290794790772312L;

	private JTextArea txtarea;
	
	public Console() {
		this(11);
	}
	
	public Console(int textSize) {
		txtarea = new JTextArea();
		txtarea.setEnabled(false);
		setViewportView(txtarea);
		txtarea.setFont(new Font("Monospaced", Font.PLAIN, textSize));
	}
	
	public void clearText() {
		txtarea.setText("");
	}
	
	public void println(String line) {
		txtarea.append(line + "\n");
	}
}
