package com.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Convert {

	protected static List<Integer> maxBitList = createMaxBitList();
	protected static List<Integer> ipv4BitList = createIPv4BitList();
	
	/* PRIVATE UTILS */
	protected static String netmaskIntegerToBinary(int netmask) {
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
	
	protected static String binaryToDecimal(String binary) {
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
	
	protected static String decimalToBinary(int decimal) {
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
	
	protected static List<Integer> createIPv4BitList() {
		List<Integer> list = new ArrayList<>();
		int value = 1;
		for (int i = 0; i < 8; i++) {
			list.add(value);
			value *= 2;
		}
		Collections.reverse(list);
		return list;
	}
	
	protected static List<Integer> createMaxBitList() {
		int max_bit_value = 1;
		int step = 1;
		List<Integer> max_bits = new ArrayList<Integer>();

		while (max_bit_value < 8 || step % 4 != 0) {
			max_bits.add((step == 1) ? max_bit_value : max_bit_value + 1);
			max_bit_value *= 2;
			step++;
		}
		max_bits.add(max_bit_value + 1);

		Collections.reverse(max_bits);
		return max_bits;
	}
	
}