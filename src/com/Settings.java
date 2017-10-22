package com;

public class Settings {

	public static boolean debug;
	
	public static final int MAX_WIDTH = 520;
	public static final int MAX_HEIGHT = 520;

	public static final String VERSION_RELEASE = "0.03.0022.050";
	
	public static String title;
	
	public static String getMainTitle(String version) {
		return "Networking - [Version: " + version + "]";
	}
}