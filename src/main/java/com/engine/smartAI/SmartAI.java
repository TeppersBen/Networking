package com.engine.smartAI;

import com.engine.components.Console;

public class SmartAI {

	protected Console console;
	
	public SmartAI(Console console) {
		this.console = console;
	}
	
	public void clearConsole() {
		console.clearText();
	}
	
}