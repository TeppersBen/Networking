package com.utils.calculators;

public class VLSMSpecializedCalculator extends NetworkConverter {
	
	private static int[] knownHosts = getKnownHosts();
	
	/**
	 * Creates an array of integers which holds all possible usable hosts in a network.
	 * @return array of all possible hosts
	 */
	public static int[] getKnownHosts() {
		int[] hostSizes = new int[32];
		int mask = 32;
		for (int i = 0; i < hostSizes.length; i++) {
			hostSizes[i] = getTotalValidHosts(mask);
			mask--;
		}
		return hostSizes;
	}
	
	/**
	 * Converts the requested host size in first following valid usable host number.
	 * @param requestedSize
	 * @return String of valid host number
	 * @Example 60 -> 62
	 */
	public static String getValidHost(int requestedSize) {
		for (int i = 0; i < knownHosts.length; i++) {
			if (requestedSize - knownHosts[i] <= 0) {
				return String.valueOf(knownHosts[i]);
			}
		}
		return "0";
	}
	
	/**
	 * Converts the requested host size into the correct CIDR notation.
	 * @param size
	 * @return String of the CIDR notation
	 * @Example 60 -> 26
	 */
	public static String getCIDR(int size) {
		size = Integer.parseInt(getValidHost(size));
		for (int i = 0; i < knownHosts.length; i++) {
			if (knownHosts[i] == size) {
				return String.valueOf(32 - i);
			}
		}
		return "0";
	}
	
	/**
	 * Converts the requested host size in to the correct decimal netmask notation.
	 * @param size
	 * @return String of decimal netmask notation
	 */
	public static String getNetmask(int size) {
		size = Integer.parseInt(getCIDR(size));
		return String.valueOf(netmaskCIDRtoDecimal(size));
	}
	
	/**
	 * Converts a major network into a vlsm view.
	 * @param network - decimal Network
	 * @param netmask - CIDR notation
	 * @return String array of network details<br>[0] = Network Address<br>[1] = HostIP Start<br>[2] = HostIP Stop<br>[3] = Broadcast IP
	 * @Example MajorNetwork: 192.168.0.1<br>Netmask: 24<br>---------------------------------<br>networkAddress: 192.168.0.0<br>hostIP Start: 192.168.0.1<br>hostIP Stop: 192.168.0.254<br>Broadcast IP: 192.168.0.255
	 */
	public static String[] calculateNetwork(String network, int netmask) {
		String[] result = new String[4];
		
		String networkAddress = getNetworkAddress(network, netmaskCIDRtoDecimal(netmask));
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
	
	/**
	 * Utility function that allows you to add an amount to a IP.
	 * @param ip
	 * @param amount
	 * @return String of new ip
	 * @Example ip: 192.168.0.10<br>amount: 252<br>-----------------<br>192.168.1.6
	 */
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