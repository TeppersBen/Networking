package com;

import java.io.File;

import com.engine.handlers.ConfigurationHandler;

public class Settings {

	public static final String CONFIGURATION_SYSTEM_FILE = "/configuration/config.properties";
	public static final String CONFIGURATION_USER_SETTINGS_FILE = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Networking" + File.separator + "config.properties";
	public static final String EXPORT_SMART_AI_LOCATION = CONFIGURATION_USER_SETTINGS_FILE + File.separator + "smartAI" + File.separator;
	
	public static boolean debug = ConfigurationHandler.getSystemKey("debug").equalsIgnoreCase("true") ? true : false;
	
	public static final int MAX_WIDTH = Integer.parseInt(ConfigurationHandler.getUserKey("windowWidth"));
	public static final int MAX_HEIGHT = Integer.parseInt(ConfigurationHandler.getUserKey("windowHeight"));

	public static String version;
	public static String releaseDate;
	
	static {
		if (debug)
			ConfigurationHandler.modifySoftwareVersion();
		version = ConfigurationHandler.getSystemKey("version");
		releaseDate = ConfigurationHandler.getSystemKey("releaseDate");
	}
	
	public static final String TITLE = ConfigurationHandler.getSystemKey("title") + ((ConfigurationHandler.getSystemKey("debug").equalsIgnoreCase("true")) ? " [Development Build: " + version + "]" : "");
	
}