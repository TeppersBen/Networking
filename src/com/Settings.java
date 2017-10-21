package com;

import com.utils.VersionCreator;

public class Settings {

	private static final int MAJOR = 0;
	private static final int MINOR = 7;
	private static final int BUGS = 12;
	
	public static final String VERSION = new VersionCreator(MAJOR, MINOR, BUGS).toString();
	
	public static final String TITLE = "Networking - [Version: " + VERSION + "]";
	public static final int MAX_WIDTH = 520;
	public static final int MAX_HEIGHT = 520;
	
	public static boolean debug;
}