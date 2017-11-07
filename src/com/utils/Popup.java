package com.utils;

import javax.swing.JOptionPane;

import com.handlers.LanguageHandler;

public class Popup {
	
	private static LanguageHandler languageHandler;

	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", languageHandler.getKey("popup_error_title"), JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showMaintenanceMessage() {
		JOptionPane.showMessageDialog(null, languageHandler.getKey("popup_maintenance_message"), languageHandler.getKey("popup_maintenance_title"), JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showInformationMessage(String message) {
		JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", languageHandler.getKey("popup_info_title"), JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showWarningMessage(String message) {
		JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", languageHandler.getKey("popup_warning_title"), JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showHelpMessage(String whatIsIt, String howDoesItWork, String example) {
		String message = "<b><u>" + languageHandler.getKey("popup_help_whatisit") + ":</u></b><br>" + whatIsIt + "<br><br>"
				+ "<b><u>" + languageHandler.getKey("popup_help_howdoesitwork") + ":</u></b><br>" + howDoesItWork + "<br><br>"
				+ "<b><u>" + languageHandler.getKey("popup_help_example") + ":</u></b><br>" + example;
		JOptionPane.showMessageDialog(null, "<html>" +  message + "<html>", "Help", JOptionPane.QUESTION_MESSAGE);
	}
	
	public static void setLanguageHandler(LanguageHandler handler) {
		languageHandler = handler;
	}
	
}