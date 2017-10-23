package com;

import com.window.WindowBuilder;

public class Launcher {

	public static void main(String[] args) {
		Settings.debug = false;
		Settings.versionRelease = "0.03.0022.050";
		new WindowBuilder(0, 3, 51);
	}
	
}