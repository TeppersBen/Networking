package com;

import com.window.WindowBuilder;

public class Launcher {

	public static void main(String[] args) {
		Settings.debug = true;
		Settings.versionRelease = "0.04.0023.053";
		new WindowBuilder(0, 4, 53);
	}
	
}