package com.utils;

import java.text.DecimalFormat;

public class Formatter {

	public static String formatInteger(int value) {
		return new DecimalFormat("###,###,###,##0").format(value).toString();
	}
	
}