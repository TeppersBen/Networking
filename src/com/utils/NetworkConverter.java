package com.utils;

import java.util.Arrays;
import java.util.List;

public class NetworkConverter extends Convert {

	private static final String[] KNOWN_NETMASKS = { "0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0",
			"255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0",
			"255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0",
			"255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252" ,"255.255.255.254", "255.255.255.255"};
	
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
	
	public static String decimalIPv4ToBinary(String ip) {
		StringBuilder result = new StringBuilder();
		List<String> segment = Arrays.asList(ip.split("\\."));
		for (int i = 0; i < 4; i++) {
			int decimal = Integer.parseInt(segment.get(i));
			result.append(decimalToBinary(decimal) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}
	
	public static String binaryIPv4ToDecimal(String ip) {
		StringBuilder result = new StringBuilder();
		List<String> segment = Arrays.asList(ip.split("\\."));
		for (int i = 0; i < 4; i++) {
			String binary = segment.get(i);
			result.append(binaryToDecimal(binary) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}

	public static String netmaskIntegerToIP(int netmask) {
		StringBuilder result = new StringBuilder(netmaskIntegerToBinary(netmask));
		List<String> segment = Arrays.asList(result.toString().split("\\."));
		result.replace(0, result.length(), "");
		for (int i = 0; i < 4; i++) {
			result.append(binaryToDecimal(segment.get(i)) + ((i < 3) ? "." : ""));
		}
		return result.toString();
	}

	public static int getTotalValidHosts(int netmask) {
		return ((int) Math.pow(2, 32 - netmask) - 2);
	}

	public static int getTotalValidSubnets(int netmask) {
		return ((int) Math.pow(2, netmask % 8));
	}

	public static String netmaskDecimalToCIDR(String netmask) {
		String[] splitter = netmask.split("\\.");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < splitter.length; i++) {
			result.append(decimalToBinary(Integer.parseInt(splitter[i])));
		}
		int cidr = result.toString().split("0")[0].length();
		return String.valueOf(cidr);
	}
	
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
	
	public static boolean isValidNetmask(String netmask) {
		boolean valid = false;
		for (int i = 0; i < KNOWN_NETMASKS.length; i++) {
			if (valid == false)
				if (KNOWN_NETMASKS[i].equalsIgnoreCase(netmask))
					valid = true;
		}
		return valid;
	}

}