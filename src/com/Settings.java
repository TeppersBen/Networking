package com;

import java.io.File;

import com.handlers.ConfigurationHandler;

public class Settings {

	public static final String CONFIGURATION_SYSTEM_FILE = "/resources/configuration/config.properties";
	public static final String CONFIGURATION_USER_SETTINGS_FILE = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Networking" + File.separator + "config.properties";
	
	public static boolean debug = ConfigurationHandler.getSystemKey("debug").equalsIgnoreCase("true") ? true : false;
	
	public static final int MAX_WIDTH = Integer.parseInt(ConfigurationHandler.getUserKey("windowWidth"));
	public static final int MAX_HEIGHT = Integer.parseInt(ConfigurationHandler.getUserKey("windowHeight"));

	public static String version;
	public static String title;
	
	static {
		if (debug)
			ConfigurationHandler.modifySoftwareVersion();
		version = ConfigurationHandler.getSystemKey("version");
		title = ConfigurationHandler.getSystemKey("title") + " - [Version: " + version + "]";
	}
}