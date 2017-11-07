package com;

import com.handlers.ConfigurationHandler;

public class Settings {

	public static final String CONFIGURATION_FILE = "src/resources/configuration/config.properties";
	
	public static boolean debug = ConfigurationHandler.getKey("debug").equalsIgnoreCase("true") ? true : false;
	
	public static final int MAX_WIDTH = Integer.parseInt(ConfigurationHandler.getKey("windowWidth"));
	public static final int MAX_HEIGHT = Integer.parseInt(ConfigurationHandler.getKey("windowHeight"));

	public static String version;
	public static String title;
	
	static {
		if (debug)
			ConfigurationHandler.modifySoftwareVersion();
		version = ConfigurationHandler.getKey("version");
		title = ConfigurationHandler.getKey("title") + " - [Version: " + version + "]";
	}
}