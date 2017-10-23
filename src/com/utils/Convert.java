package com.utils;

import java.util.Arrays;
import java.util.List;

public class Convert {

	public static List<Integer> maxBitList = createMaxBitList();
	public static List<Integer> ipv4BitList = createIPv4BitList();
	
	/* PRIVATE UTILS */
	public static String netmaskIntegerToBinary(int netmask) {
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
	
	public static String binaryToDecimal(String binary) {
		StringBuilder result = new StringBuilder(binary);
		int value = 0;

		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == '1') {
				value += ipv4BitList.get(i);
			}
		}

		result.replace(0, result.length(), String.valueOf(value));
		return result.toString();
	}
	
	public static String decimalToBinary(int decimal) {
		StringBuilder result = new StringBuilder();

		int aantal_bits = 0;
		for (int i = 0; i < maxBitList.size(); i++) {
			if (decimal - maxBitList.get(i) > 0 || aantal_bits % 4 != 0) {
				aantal_bits++;
			}
		}

		for (int i = 0; i < ipv4BitList.size(); i++) {
			if (decimal - ipv4BitList.get(i) < 0) {
				result.append("0");
			} else {
				decimal -= ipv4BitList.get(i);
				result.append("1");
			}
		}

		return result.toString();
	}
	
	public static List<Integer> createIPv4BitList() {
		return Arrays.asList(128, 64, 32, 16, 8, 4, 2, 1);
	}
	
	public static List<Integer> createMaxBitList() {
		return Arrays.asList(15, 7, 3, 1);
	}
	
}