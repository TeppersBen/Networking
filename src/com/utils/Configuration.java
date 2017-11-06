package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Configuration {
	
	public static String getKey(String key) {
		try {
			File configFile = new File("src/resources/config.properties");
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
	
	private static void modifyKey(String key, String value) {
		try {
			File configFile = new File("src/resources/config.properties");
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
	
	public static void modifySoftwareVersion() {
		int major = Integer.parseInt(getKey("major"));
		int minor = Integer.parseInt(getKey("minor"));
		int bugsFixed = Integer.parseInt(getKey("bugsFixed"));
		modifyKey("version", new VersionCreator(major, minor, bugsFixed).toString());
	}
	
	public static void modifyLanguage(String language, String country) {
		modifyKey("locale", language + "_" + country);
	}
}