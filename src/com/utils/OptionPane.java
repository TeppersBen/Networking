package com.utils;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class OptionPane {

	public static void showErrorMessage(String message) {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Error Message", JOptionPane.ERROR_MESSAGE);
		});
	}
	
}