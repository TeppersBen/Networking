package com.engine.smartAI;

import com.engine.calculators.NetworkConverter;
import com.engine.calculators.VLSMSpecializedCalculator;
import com.engine.components.Console;

public class SmartAIDHCP extends SmartAI {

	public SmartAIDHCP(Console console) {
		super(console);
	}
	
	public void exec(String poolName, String network, String dnsServer, String excludedIPs) {
		enterConfigurationTerminal();
		String net = VLSMSpecializedCalculator.getNetworkAddress(network.split("/")[0], NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(network.split("/")[1])));
		String mask = NetworkConverter.netmaskCIDRtoDecimal(Integer.parseInt(network.split("/")[1]));
		createDCHPPool(poolName + "_" + net);
		createDHCPNetwork(net, mask, dnsServer);
		exitDHCPconfig();
		setExcludedIPs(excludedIPs);
	}
	
	private void enterConfigurationTerminal() {
		console.println("Router>enable");
		console.println("Router#config terminal");
	}
	
	private void createDCHPPool(String poolName) {
		console.println("Router(config)#ip dhcp pool " + poolName);
	}
	
	private void createDHCPNetwork(String network, String mask, String dnsServer) {
		console.println("Router(dhcp-config)#network " + network + " " + mask);
		console.println("Router(dhcp-config)#dns-server " + dnsServer);
		console.println("Router(dhcp-config)#default-router " + (VLSMSpecializedCalculator.ipAdd(network, 1)));
	}
	
	private void exitDHCPconfig() {
		console.println("Router(dhcp-config)#exit");
	}
	
	private void setExcludedIPs(String excludedIPs) {
		excludedIPs = excludedIPs.replaceAll("\n", "").replace("\r", "");
		String[] ranges = excludedIPs.split(",");
		for (int i = 0; i < ranges.length; i++) {
			console.println("Router(config)#ip dhcp excluded-address " + ranges[i]);
		}
		console.println("Router(config)#end");
	}
}