package com;

import com.window.WindowBuilder;

public class Launcher {

	public static void main(String[] args) {
		Settings.debug = false;
		Settings.versionRelease = "0.04.0024.054";
		new WindowBuilder(0, 4, 54);
	}
	
}