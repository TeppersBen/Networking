package com;

import com.window.WindowBuilder;

public class Launcher {

	public static void main(String[] args) {
		Settings.debug = false;
		new WindowBuilder(0, 3, 80);
	}
	
}