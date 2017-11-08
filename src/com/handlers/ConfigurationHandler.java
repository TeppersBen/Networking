package com.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import com.Settings;
import com.utils.VersionCreator;

public class ConfigurationHandler {
	
	private static String getKey(String file, String key) {
		try {
			File configFile = new File(file);
			if (!configFile.exists()) 
				createDefaultProperties();
			FileInputStream fis = new FileInputStream(configFile);
			Properties config = new Properties();
			config.load(fis);
			fis.close();
			return config.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static String getSystemKey(String key) {
		try {
			Properties config = new Properties();
			InputStream in = ConfigurationHandler.class.getResourceAsStream(Settings.CONFIGURATION_SYSTEM_FILE);
			config.load(in);			
			in.close();
			return config.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static String getUserKey(String key) {
		return getKey(Settings.CONFIGURATION_USER_SETTINGS_FILE, key);
	}
	
	public static void modifyUserKey(String key, String value) {
		modifyKey(Settings.CONFIGURATION_USER_SETTINGS_FILE, key, value);
	}
	
	private static void modifyKey(String file, String key, String value) {
		try {
			File configFile = new File(file);
			Properties config = new Properties();
			
			FileInputStream fis = new FileInputStream(configFile);
			config.load(fis);
			fis.close();

			config.setProperty(key, value);
			
			FileOutputStream fos = new FileOutputStream(configFile);
			config.store(fos, "Program Settings");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void createDefaultProperties() {
		try {
			File configFile = new File(Settings.CONFIGURATION_USER_SETTINGS_FILE.substring(0, Settings.CONFIGURATION_USER_SETTINGS_FILE.length()-17), "config.properties");
			configFile.getParentFile().mkdirs();
			Properties config = new Properties();
			
			config.setProperty("locale", "en_GB");
			config.setProperty("windowWidth", "520");
			config.setProperty("windowHeight", "520");
			
			FileOutputStream fos = new FileOutputStream(configFile);
			config.store(fos, "User Settings");
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void modifySoftwareVersion() {
		int major = Integer.parseInt(getSystemKey("major"));
		int minor = Integer.parseInt(getSystemKey("minor"));
		int bugsFixed = Integer.parseInt(getSystemKey("bugsFixed"));
		modifyKey("src/" + Settings.CONFIGURATION_SYSTEM_FILE, "version", new VersionCreator(major, minor, bugsFixed).toString());
	}
	
	public static void modifyLanguage(String language, String country) {
		modifyUserKey("locale", language + "_" + country);
	}
}