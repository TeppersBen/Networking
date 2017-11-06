package com;

import com.utils.Configuration;

public class Settings {

	public static boolean debug = Configuration.getKey("debug").equalsIgnoreCase("true") ? true : false;
	
	public static final int MAX_WIDTH = 520;
	public static final int MAX_HEIGHT = 520;

	public static String version;
	public static String title;
	
	static {
		if (debug)
			Configuration.modifySoftwareVersion();
		version = Configuration.getKey("version");
		title = Configuration.getKey("title") + " - [Version: " + version + "]";
	}
}