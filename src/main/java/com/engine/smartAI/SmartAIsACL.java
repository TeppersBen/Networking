package com.engine.smartAI;

import com.engine.calculators.NetworkConverter;

public class SmartAIsACL extends SmartAI {
/*
	public SmartAIsACL(Console console) {
		super(console);
	}
	
	public void exec(String listName, String firstIP, String lastIP, String interfaceName) {
		enterConfigurationMode();
		createsACLList(listName);
		createsACLElements(firstIP, lastIP);
		enterInterface(interfaceName);
		mountList(listName);
	}
	
	private void enterConfigurationMode() {
		console.println("Router>enable");
		console.println("Router#config terminal");
	}
	
	private void createsACLList(String listName) {
		console.println("Router(config)#ip access-list standard " + listName);
	}
	
	private void createsACLElements(String firstIP, String lastIP) {
		console.println("Router(config-std-nacl)#permit " + firstIP + " " + (NetworkConverter.getRequestedWildcard(firstIP, lastIP)));
		console.println("Router(config-std-nacl)#deny any");
		console.println("Router(config-std-nacl)#exit");
	}
	
	private void enterInterface(String interfaceName) {
		console.println("Router(config)#int " + interfaceName);
	}
	
	private void mountList(String listName) {
		console.println("Router(config-if)#ip access-group " + listName + " out");
		console.println("Router(config-if)#end");
	}
*/
}
