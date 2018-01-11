package com.engine.calculators;

import java.util.Arrays;
import java.util.List;

public class NetworkConverter extends Convert {

	private static final String[] KNOWN_NETMASKS = { "0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0",
			"255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0",
			"255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0",
			"255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252" ,"255.255.255.254", "255.255.255.255"};
	
	/**
	 * Converts a netmask CIDR notation to a binary value.
	 * @param netmask
	 * @return String of the binary value
	 * @Example 5 -> 11111000.00000000.00000000.00000000
	 */
	public static String netmaskCIDRtoBinary(int netmask) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < netmask; i++) {
			result.append("1" + ((i != 0 && (i + 1) % 8 == 0) ? "." : ""));
		}
		
		int yetToGo = 32 - netmask;
		for (int i = yetToGo; i > 0; i--) {
			result.append("0" + (((i-1) % 8 == 0 && i != 1) ? "." : ""));
		}
		return result.toString();
	}
	
	/**
	 * Converts a netmask IP to the current class it is in.
	 * @param netmaskIP
	 * @return String of the current class.
	 * @Example 255.0.0.0 -> A
	 */
	public static String getNetmaskClass(String netmaskIP) {
		int x = 0;
		String[] splitter = netmaskIP.split("\\.");
		for (int i = 0; i < splitter.length; i++) {
			if (Integer.parseInt(splitter[i]) == 255) {
				x++;
			}
		}
		switch (x) {
		case 1:
			return "A";
		case 2:
			return "B";
		case 3:
			return "C";
		case 4:
			return "D";
		default: 
			return "/";
		}
	}
	
	/**
	 * Converts a decimal IPv4 notation to a binary value.
	 * @param ip
	 * @return String of the binary value.
	 * @Example 192.168.0.1 -> 11000000.10101000.00000000.00000001
	 */
	public static String decimalIPv4ToBinary(String ip) {
		StringBuilder result = new StringBuilder();
		List<String> segment = Arrays.asList(ip.split("\\."));
		for (int i = 0; i < 4; i++) {
			int decimal = Integer.parseInt(segment.get(i));
			result.append(decimalToBinary(decimal) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}
	
	/**
	 * Converts a binary IPv4 notation to a decimal value.
	 * @param ip
	 * @return String of the decimal value.
	 * @Example 00001010.00000000.00000000.00000001 -> 10.0.0.1
	 */
	public static String binaryIPv4ToDecimal(String ip) {
		StringBuilder result = new StringBuilder();
		List<String> segment = Arrays.asList(ip.split("\\."));
		for (int i = 0; i < 4; i++) {
			String binary = segment.get(i);
			result.append(binaryToDecimal(binary) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}

	/**
	 * Converts a netmask CIDR notation to a decimal value.
	 * @param netmask
	 * @return String of the decimal value.
	 * @Example 24 -> 255.255.255.0
	 */
	public static String netmaskCIDRtoDecimal(int netmask) {
		StringBuilder result = new StringBuilder(netmaskCIDRtoBinary(netmask));
		List<String> segment = Arrays.asList(result.toString().split("\\."));
		result.replace(0, result.length(), "");
		for (int i = 0; i < 4; i++) {
			result.append(binaryToDecimal(segment.get(i)) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}

	/**
	 * Converts the netmask CIDR notation to max_possible_hosts in that CIDR.
	 * @param netmask
	 * @return total usable hosts.
	 * @Example 24 -> 254
	 */
	public static int getTotalValidHosts(int netmask) {
		return ((int) Math.pow(2, 32 - netmask) - 2);
	}

	/**
	 * Converts the netmask CIDR notation to max_possible_subnets in that CIDR.
	 * @param netmask
	 * @return total usable subnets.
	 * @Example 24 -> 1
	 */
	public static int getTotalValidSubnets(int netmask) {
		return ((int) Math.pow(2, netmask % 8));
	}

	/**
	 * Converts a netmask decimal notation to a CIDR notation.
	 * @param netmask
	 * @return String of the CIDR notation.
	 * @Example 255.255.0.0 -> 16
	 */
	public static String netmaskDecimalToCIDR(String netmask) {
		String[] splitter = netmask.split("\\.");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < splitter.length; i++) {
			result.append(decimalToBinary(Integer.parseInt(splitter[i])));
		}
		int cidr = result.toString().split("0")[0].length();
		return String.valueOf(cidr);
	}
	
	/**
	 * Compares two binary values and uses the AND-binary-logic-controller.<br>
	 * The two binary values have to be IPv4 binary addresses in order to complete this method.
	 * @param a
	 * @param b
	 * @return String of the compared binary value.
	 * @Example 11110000.10101111.00010001.00000111 <br> 11110000.11111111.01110101.00000111 <br> --------------------------------------------- <br> 11110000.10101111.00010001.00000111
	 */
	public static String operatorAND(String a, String b) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 35; i++) {
			if (a.charAt(i) == '.' && b.charAt(i) == '.') {
				result.append(".");
			} else if (a.charAt(i) == '1' && b.charAt(i) == '1') {
				result.append("1");
			} else {
				result.append("0");
			}
		}
		return result.toString();
	}
	
	/**
	 * Checks if the netmask decimal notation is valid.
	 * @param netmask
	 * @return true if netmask is valid.
	 * @Example 255.255.128.0 -> true (valid) <br> 257.287.024.253 -> false (invalid)
	 */
	public static boolean isValidNetmask(String netmask) {
		boolean valid = false;
		for (int i = 0; i < KNOWN_NETMASKS.length; i++) {
			if (valid == false)
				if (KNOWN_NETMASKS[i].equalsIgnoreCase(netmask))
					valid = true;
		}
		return valid;
	}
	
	/**
	 * Converts a Decimal Netmask to its Wildcard.
	 * @param decimalNetmask - String of decimal subnet mask
	 * @return string of Wildcard
	 * @Example 255.255.0.0 > 0.0.255.255
	 */
	public static String getWildCardMask(String decimalNetmask) {
		String[] segments = decimalNetmask.split("\\.");
		String result = "";
		for (int j = 0; j < 4; j++) {
			result += (255 - Integer.parseInt(segments[j])) + ((j == 3) ? "" : ".");
		}
		return result;
	}
	
	/**
	 * Converts a CIDR notation to its Wildcard.
	 * @param cidr - network range notation
	 * @return string of wildcard
	 * @Example 24 > 0.0.0.255
	 */
	public static String getWildCardMask(int cidr) {
		return getWildCardMask(netmaskCIDRtoDecimal(cidr));
	}
	
	/**
	 * Converts two ip addresses and returns the best possible wildcard.
	 * @param firstIP - First IP address in the requested range
	 * @param lastIP - Last IP address in the requested range
	 * @return wildcard of requested ACL range
	 * @Example <b>firstIP:</b> 10.0.1.16<br><b>lastIP:</b> 10.0.1.17<br><b>------------------<br>Wildcard:</b> 0.0.0.1
	 */
	public static String getRequestedWildcard(String firstIP, String lastIP) {
		String[] firstIPSegments = firstIP.split("\\.");
		String[] lastIPSegments = lastIP.split("\\.");
		String result = "";
		for (int i = 0; i < 4; i++) {
			result += (Integer.parseInt(lastIPSegments[i]) - Integer.parseInt(firstIPSegments[i])) + ((i == 3) ? "" : ".");
		}
		return result;
	}

}