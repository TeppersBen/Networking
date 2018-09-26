package com.engine.calculators;

import java.util.Arrays;
import java.util.List;

public class NetworkConverter extends Convert {
	
	/**
	 * Converts a netmask CIDR notation to a binary value.<br>
	 * I.E.: 5 -> 11111000.00000000.00000000.00000000
	 * @param netmask int
	 * @return String of the binary value
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
	 * Converts a netmask IP to the current class it is in.<br>
	 * I.E.: 255.0.0.0 -> A
	 * @param netmaskIP String
	 * @return String of the current class.
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
	 * Converts a decimal IPv4 notation to a binary value.<br>
	 * I.E.: 192.168.0.1 -> 11000000.10101000.00000000.00000001
	 * @param ip String
	 * @return String of the binary value.
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
	 * Converts a binary IPv4 notation to a decimal value.<br>
	 * I.E.: 00001010.00000000.00000000.00000001 -> 10.0.0.1
	 * @param ip String
	 * @return String of the decimal value.
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
	 * Converts a netmask CIDR notation to a decimal value.<br>
	 * I.E.: 24 -> 255.255.255.0
	 * @param netmask int
	 * @return String of the decimal value.
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
	 * Converts the netmask CIDR notation to max_possible_hosts in that CIDR.<br>
	 * I.E.: 24 -> 254
	 * @param netmask int
	 * @return total usable hosts.
	 */
	public static int getTotalValidHosts(int cidr) {
		return ((int) Math.pow(2, 32 - cidr) - 2);
	}

	/**
	 * Converts the CIDR notation to max_possible_subnets in that CIDR.<br>
	 * I.E.: 24 -> 1
	 * @param netmask int
	 * @return total usable subnets.
	 */
	public static int getTotalValidSubnets(int cidr) {
		return ((int) Math.pow(2, cidr % 8));
	}

	/**
	 * Converts a netmask decimal notation to a CIDR notation.<br>
	 * I.E.: 255.255.0.0 -> 16
	 * @param netmask String
	 * @return String of the CIDR notation.
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
	 * The two binary values have to be IPv4 binary addresses in order to complete this method.<br>
	 * <code>
	 *     11110000.10101111.00010001.00000111<br>
	 *     11110000.11111111.01110101.00000111<br>
	 *     -----------------------------------<br>
	 *     11110000.10101111.00010001.00000111
	 * </code>
	 * @param a String
	 * @param b String
	 * @return String of the compared binary value.
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
	 * Converts a Decimal Netmask to its Wildcard.
	 * @param decimalNetmask String of decimal subnet mask
	 * @return String of Wildcard
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
	 * Converts a CIDR notation to its Wildcard.<br>
	 * 24 > 0.0.0.255
	 * @param cidr network range notation
	 * @return string of wildcard
	 */
	public static String getWildCardMask(int cidr) {
		return getWildCardMask(netmaskCIDRtoDecimal(cidr));
	}
	
	/**
	 * Converts two ip addresses and returns the best possible wildcard.<br>
	 * <b>firstIP:</b> 10.0.1.16<br><b>lastIP:</b> 10.0.1.17<br><b>------------------<br>Wildcard:</b> 0.0.0.1
	 * @param firstIP First IP address in the requested range
	 * @param lastIP Last IP address in the requested range
	 * @return wildcard of requested ACL range
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