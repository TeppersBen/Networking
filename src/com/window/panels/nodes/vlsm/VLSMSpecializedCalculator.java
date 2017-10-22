package com.window.panels.nodes.vlsm;

import com.utils.NetworkConverter;

public class VLSMSpecializedCalculator extends NetworkConverter {
	
	private static int[] knownHosts = getKnownHosts();
	
	public static int[] getKnownHosts() {
		int[] hostSizes = new int[32];
		int mask = 32;
		for (int i = 0; i < hostSizes.length; i++) {
			hostSizes[i] = getTotalValidHosts(mask);
			mask--;
		}
		return hostSizes;
	}
	
	public static String getValidHost(int requestedSize) {
		for (int i = 0; i < knownHosts.length; i++) {
			if (requestedSize - knownHosts[i] <= 0) {
				return String.valueOf(knownHosts[i]);
			}
		}
		return "0";
	}
	
	public static String getCIDR(int size) {
		size = Integer.parseInt(getValidHost(size));
		for (int i = 0; i < knownHosts.length; i++) {
			if (knownHosts[i] == size) {
				return String.valueOf(32 - i);
			}
		}
		return "0";
	}
	
	public static String getNetmask(int size) {
		size = Integer.parseInt(getCIDR(size));
		return String.valueOf(netmaskIntegerToIP(size));
	}
	
	/**
	 * [0] = Network address
	 * [1] = HostIP start
	 * [2] = HostIP stop
	 * [3] = Broadcast IP
	 * @param input
	 * @return
	 */
	public static String[] calculateNetwork(String input, int netmask) {
		String[] result = new String[4];
		String[] splitter = input.split("/");
		String ip = splitter[0];
		
		String networkAddress = getNetworkAddress(ip, netmaskIntegerToIP(netmask));
		String hostIPStart = getHostIPStart(networkAddress);
		String hostIPStop = getHostIPStop(networkAddress, getTotalValidHosts(netmask));	
		String broadcastIP = getBroadcastAddress(hostIPStop);
		
		result[0] = networkAddress;
		result[1] = hostIPStart;
		result[2] = hostIPStop;
		result[3] = broadcastIP;
		
		return result;
	}
	
	private static String getNetworkAddress(String ip, String mask) {
		String and = operatorAND(decimalIPv4ToBinary(ip), decimalIPv4ToBinary(mask));
		String[] remind = and.split("\\.");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			result.append(binaryToDecimal(remind[i]) + ((i == 3) ? "" : "."));
		}
		return result.toString();
	}
	
	private static String getHostIPStart(String network) {
		return ipAdd(network, 1);
	}
	
	private static String getHostIPStop(String network, int amount) {
		return ipAdd(network, amount);
	}
	
	private static String getBroadcastAddress(String network) {
		return ipAdd(network, 1);
	}
	
	public static String ipAdd(String ip, int amount) {
		int values[] = new int[4];
		String[] net = ip.split("\\.");
		for (int i = 3; i >= 0; i--) {
			values[i] = Integer.parseInt(net[i]);
		}
		values[3] += amount;
		if (values[3] > 255) {
			values[2] += values[3] / 256;
			values[3] %= 256;
		} 
		if (values[2] > 255) {
			values[1] += values[2] / 256;
			values[2] %= 256;
		}
		if (values[1] > 255) {
			values[0] += values[1] / 256;
			values[1] %= 256;
		} 
		if (values[0] > 255) {
			values[0] = 255;
		}
		return "" + values[0] + "." + values[1] + "." + values[2] + "." + values[3];
	}
	
}