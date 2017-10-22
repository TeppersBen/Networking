package com;

public class Settings {

	public static boolean debug;
	
	public static final int MAX_WIDTH = 520;
	public static final int MAX_HEIGHT = 520;

	public static String versionRelease;
	
	public static String title;
	
	public static String getMainTitle(String version) {
		return "Networking - [Version: " + version + "]";
	}
}