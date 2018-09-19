package com.engine.utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import com.Settings;

public class Logger {

	private static JTextPane area;

	private static Thread logThread;

	public static void setLoggingField(JTextPane field) {
		area = field;
		area.setFont(new Font(area.getFont().toString(), area.getFont().getStyle(), 11));
		log("Initializing System...");
	}

	public static synchronized void log(String message) {
		writeLine(message, Color.black);
	}

	public static synchronized void error(String message) {
		writeLine(message, Color.red);
	}
	
	public static synchronized void debugLog(String message) {
		if (Settings.debug)
			writeLine(message, Color.black);
	}

	private static void appendToPane(String msg, Color c) {
		area.setEditable(true);
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
		area.replaceSelection(((area.getDocument().getLength() == 0) ? "" : "\n") + msg);
		
		int len = area.getDocument().getLength();
		area.setCaretPosition(len);
		area.setCharacterAttributes(aset, false);
		area.setEditable(false);
	}
	
	private static synchronized void writeLine(String message, Color color) {
		if (area == null)
			return;
		logThread = new Thread(() -> {
			appendToPane(message, color);
		});
		logThread.start();
		try {
			logThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}