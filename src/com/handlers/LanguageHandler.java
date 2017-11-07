package com.handlers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHandler {

	private Locale locale;
	private ResourceBundle bundle;
	private String localeString = ConfigurationHandler.getKey("locale");
	
	public LanguageHandler() {
		locale = new Locale(localeString.split("_")[0], localeString.split("_")[1]);
		bundle = ResourceBundle.getBundle("resources.language.language", locale);
	}
	
	public String getKey(String key) {
		return bundle.getString(key);
	}
	
	public void changeLanguage(String language, String country) {
		locale = new Locale(language, country);
		bundle = ResourceBundle.getBundle("resources.language.language", locale);
		ConfigurationHandler.modifyLanguage(language, country);
	}
	
	public String getLocale() {
		return locale.toString();
	}
	
}