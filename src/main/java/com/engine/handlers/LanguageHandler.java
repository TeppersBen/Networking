package com.engine.handlers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHandler {

	private static Locale locale;
	private static ResourceBundle bundle;
	private static String localeString = ConfigurationHandler.getUserKey("locale");

	static {
		locale = new Locale(localeString.split("_")[0], localeString.split("_")[1]);
		bundle = ResourceBundle.getBundle("language.language", locale);
	}
	
	public static String getKey(String key) {
		return bundle.getString(key);
	}
	
	public static void changeLanguage(String language, String country) {
		ConfigurationHandler.modifyLanguage(language, country);
	}
	
	public static String getLocale() {
		return locale.toString();
	}
	
}