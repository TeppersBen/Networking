package com.engine.utils;

import java.text.DecimalFormat;

public class Formatter {

	public static String formatInteger(int value) {
		return new DecimalFormat("###,###,###,##0").format(value).toString();
	}
	
	public static String formatMessage(int length, String message) {
		int value = 0;
		String words[] = message.split(" ");
		String result = "";
		for (int i = 0; i < words.length; i++) {
			value += words[i].length();
			if (value < length) {
				result += words[i] + " ";
			} else {
				result += "<br>" + words[i] + " ";
				value = 0;
			}
		}
		return result;
	}
	
}