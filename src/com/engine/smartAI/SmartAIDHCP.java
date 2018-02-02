package com.engine.smartAI;

import com.engine.calculators.NetworkConverter;
import com.engine.calculators.VLSMSpecializedCalculator;
import com.engine.components.Console;
import com.engine.components.TextArea;
import com.engine.components.TextField;
import com.engine.handlers.ValidatorHandler;

public class SmartAIDHCP extends SmartAI {

	public SmartAIDHCP(Console console) {
		super(console);
	}
	
	public void exec(String poolName, String network, TextField dnsServer, TextArea excludedIPs) {
		enterConfigurationTerminal();
		String net = VLSMSpecializedCalculator.getNetworkAddress(network.split("/")[0], NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(network.split("/")[1])));
		String mask = NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(network.split("/")[1]));
		createDCHPPool(poolName + "_" + net);
		createDHCPNetwork(net, mask);
		if (!dnsServer.isEmpty())
			createDNSServer(dnsServer.getText());
		exitDHCPconfig();
		if (!excludedIPs.isEmpty())
			setExcludedIPs(excludedIPs.getText());
		console.println("Router(config)#end");
	}
	
	private void enterConfigurationTerminal() {
		console.println("Router>enable");
		console.println("Router#config terminal");
	}
	
	private void createDCHPPool(String poolName) {
		console.println("Router(config)#ip dhcp pool " + poolName);
	}
	
	private void createDHCPNetwork(String network, String mask) {
		console.println("Router(dhcp-config)#network " + network + " " + mask);
		console.println("Router(dhcp-config)#default-router " + (VLSMSpecializedCalculator.ipAdd(network, 1)));
	}
	
	private void createDNSServer(String dnsServer) {
		console.println("Router(dhcp-config)#dns-server " + dnsServer);
	}
	
	private void exitDHCPconfig() {
		console.println("Router(dhcp-config)#exit");
	}
	
	private void setExcludedIPs(String excludedIPs) {
		excludedIPs = excludedIPs.replaceAll("\n", "").replace("\r", "");
		String[] ranges = excludedIPs.split(",");
		for (int i = 0; i < ranges.length; i++) {
			if (ranges[i].contains("-")) {
				String[] splitter = ranges[i].split("-");
				if (splitter.length == 2) {
					splitter[0] = splitter[0].replaceAll(" ", "");
					splitter[1] = splitter[1].replaceAll(" ", "");
					if (ValidatorHandler.isValidIPv4Address(splitter[0])
					 || ValidatorHandler.isValidIPv4Address(splitter[1]))
						console.println("Router(config)#ip dhcp excluded-address " + splitter[0] + " - " + splitter[1]);
				}
				
			}
			else if (ValidatorHandler.isValidIPv4Address(ranges[i]))
				console.println("Router(config)#ip dhcp excluded-address " + ranges[i]);
		}
	}
}