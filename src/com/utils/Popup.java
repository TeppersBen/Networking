package com.utils;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Popup {

	public static void showErrorMessage(String message) {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Error", JOptionPane.ERROR_MESSAGE);
		});
	}
	
	public static void showMaintenanceMessage() {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "Developers are working on this part of the application.", "Maintenance", JOptionPane.INFORMATION_MESSAGE);
		});
	}
	
	public static void showInformationMessage(String message) {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Information", JOptionPane.INFORMATION_MESSAGE);
		});
	}
	
	public static void showWarningMessage(String message) {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Warning", JOptionPane.WARNING_MESSAGE);
		});
	}
	
	public static void showHelpMessage(String whatIsIt, String howDoesItWork, String example) {
		String message = "<b><u>What Is It:</u></b><br>" + whatIsIt + "<br><br><b><u>How Does It Work:</u></b><br>" + howDoesItWork + "<br><br><b><u>Example:</u></b><br>" + example;
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Help", JOptionPane.INFORMATION_MESSAGE);
		});
	}
	
}