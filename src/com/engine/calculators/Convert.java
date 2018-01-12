package com.engine.calculators;

import java.util.Arrays;
import java.util.List;

public class Convert {

	public static List<Integer> maxBitList = createMaxBitList();
	public static List<Integer> ipv4BitList = createIPv4BitList();

	/**
	 * Converts a binary value to a decimal value.
	 * @param binary
	 * @return String of the decimal value.
	 * @Example 11110000 -> 240
	 */
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
	
	/**
	 * Converts a decimal value to a binary value.
	 * @param decimal
	 * @return String of the binary value.
	 * @Example 20 -> 00010100
	 */
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
	
	/**
	 * Creates a list of the 8 bit sequence in reverse.
	 * @return List [128, 64, 32, 16, 8, 4, 2, 1]
	 */
	public static List<Integer> createIPv4BitList() {
		return Arrays.asList(128, 64, 32, 16, 8, 4, 2, 1);
	}
	
	/**
	 * Creates a list of the 4 bit max_value sequence in reverse.
	 * @return List [15, 7, 3, 1]
	 */
	public static List<Integer> createMaxBitList() {
		return Arrays.asList(15, 7, 3, 1);
	}
	
}