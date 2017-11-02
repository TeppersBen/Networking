package com.handlers;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHandler {

	private Locale locale;
	private ResourceBundle bundle;
	
	public LanguageHandler() {
		locale = new Locale("en", "GB");
		bundle = ResourceBundle.getBundle("resources.language.language", locale);
	}
	
	public String getKey(String key) {
		return bundle.getString(key);
	}
	
	public void changeLanguage(String language, String country) {
		locale = new Locale(language, country);
		bundle = ResourceBundle.getBundle("resources.language.language", locale);
	}
	
	public String getLocale() {
		return locale.toString();
	}
	
}