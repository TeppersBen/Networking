package com.devices;

public class Router extends NetworkDevice {

	public Router() {
		super(Devices.ROUTER);
	}

	@Override
	public String issueCommand(String command) throws Exception {
		String suffix = "";
		// filter command
		if (getState() == State.LOCKED)
			command = command.split(">")[command.split(">").length - 1].trim();
		else
			command = command.split("#")[command.split("#").length - 1].trim();
		// modify command if aquired
		if (!command.contains("no hostname") && command.contains("hostname") && command.split(" ").length == 2) {
			suffix = command.split("#")[1].split(" ")[1];
			command = command.split("#")[1].split(" ")[0];
		}
		// default command scan
		switch (command) {
		case "":
			return getCurrentState();
		case "exit":
			return commandExit();
		case "enable":
			return commandEnable();
		case "disable":
			return commandDisable();
		case "configure terminal":
		case "config terminal":
		case "config t":
			return commandConfigTerminal(command);
		case "hostname":
			return commandHostName(suffix);
		case "no hostname":
			return commandNoHostname();
		case "end":
			return commandEnd();
		case "?":
			return commandQuestionMark();
		default:
			return defaultExecution(command);
		}
	}

	public String defaultExecution(String command) throws Exception {
		if (getState() == State.LOCKED)
			return "Translating '" + command
					+ "'...domain server (255.255.255.255)\n% Unknown command or computer name, or unable to find computer address\n\n"
					+ getCurrentState();
		else
			return "% Unknown command...\n" + getCurrentState();
	}

	private String commandConfigTerminal(String command) throws Exception {
		if (getState() != State.UNLOCKED)
			return defaultExecution(command);
		changeState(State.CONFIGURATION);
		return getCurrentState();
	}

	private String commandExit() throws Exception {
		if (getState() == State.CONFIGURATION)
			changeState(State.UNLOCKED);
		else if (getState() == State.LINE_CONSOLE)
			changeState(State.CONFIGURATION);
		else if (getState() == State.INTERFACE)
			changeState(State.CONFIGURATION);
		else if (getState() == State.UNLOCKED)
			throw new Exception(defaultExecution("exit"));
		return getCurrentState();
	}

	private String commandEnable() throws Exception {
		if (getState() == State.UNLOCKED)
			throw new Exception(defaultExecution("enable"));
		changeState(State.UNLOCKED);
		return getCurrentState();
	}

	private String commandDisable() throws Exception {
		if (getState() != State.UNLOCKED)
			throw new Exception(defaultExecution("disable"));
		changeState(State.LOCKED);
		return getCurrentState();
	}

	private String commandHostName(String suffix) throws Exception {
		if (getState() != State.UNLOCKED)
			return defaultExecution("hostname");
		if (suffix.equalsIgnoreCase(""))
			return "% Incomplete command...\n" + getCurrentState();
		setName(suffix);
		return getCurrentState();
	}

	private String commandNoHostname() throws Exception {
		return commandHostName(getDefaultName());
	}

	private String commandEnd() throws Exception {
		if (getState() != State.INTERFACE)
			throw new Exception(defaultExecution("end"));
		changeState(State.UNLOCKED);
		return getCurrentState();
	}

	private String commandQuestionMark() {
		if (getState() == State.LOCKED) {
			return createCommandsList(
				new String[][] { 
					{ "<1-99>", "Session number to resume" },
					{ "connect", "Open a terminal connection" },
					{ "disable", "Turn off privileged commands" },
					{ "exit", "Exit from the EXEC" },
					{ "logout" , "Exit from the EXEC" },
					{ "ping", "Send echo messages" },
					{ "resume", "Resume an active network connection" },
					{ "show", "Show running system information" },
					{ "ssh", "Open a secure shell client connection" },
					{ "telnet", "Open telnet connection" },
					{ "terminal", "Set terminal line parameters" },
					{ "traceroute", "Trace route to destination" }
				});
		} else {
			return "";
		}
	}
}