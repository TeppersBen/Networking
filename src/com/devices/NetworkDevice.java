package com.devices;

public abstract class NetworkDevice {

	private String name;
	private String defaultName;
	private Boolean locked;
	private State currentState;
	
	public NetworkDevice(Devices type) {
		setType(type);
		currentState = State.LOCKED;
	}

	public abstract String issueCommand(String command) throws Exception;
	
	private void setType(Devices type) {
		switch (type) {
		case ROUTER:
			name = "Router";
			break;
		case SWITCH:
			name = "Switch";
			break;
		default:
			name = "unknown";
			break;
		}
		defaultName = name;
	}
	
	private String prefix(State state) {
		switch (state) {
		case LOCKED:
			return ">";
		case UNLOCKED:
			return "#";
		case CONFIGURATION:
			return "(config)#";
		case LINE_CONSOLE:
			return "(config-line)#";
		case INTERFACE:
			return "(config-if)#";
		default:
			return "(UNKNOWN)#";
		}
	}
	
	public void changeState(State state) {
		currentState = state;
	}
	
	public State getState() {
		return currentState;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getLocked() {
		return locked;
	}
	
	public String getCurrentState() {
		return name + prefix(currentState);
	}
	
	public enum State {
		LOCKED, UNLOCKED, CONFIGURATION, LINE_CONSOLE, INTERFACE
	}
	
	public String getDefaultName() {
		return defaultName;
	}
	
	protected String createCommandsList(String[][] commands) {
		String result = "";
		for (int i = 0; i < commands.length; i++) {
			result += String.format("  %-11s %s%n", commands[i][0], commands[i][1]);
		}
		return "Exec commands:\n" + result + getCurrentState();
	}
}