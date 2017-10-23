package com;

import com.window.WindowBuilder;

public class Launcher {

	public static void main(String[] args) {
		Settings.debug = true;
		Settings.versionRelease = "0.03.0022.050";
		new WindowBuilder(0, 4, 52);
	}
	
}