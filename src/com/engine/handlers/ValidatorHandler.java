package com.engine.handlers;

import com.engine.utils.Popup;

public class ValidatorHandler {

	private static LanguageHandler languageHandler;
	
	private static final String[] KNOWN_NETMASKS = { "0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0",
			"255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0",
			"255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0",
			"255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252" ,"255.255.255.254", "255.255.255.255"};	
	
	public static void mountLanguageHandler(LanguageHandler lang) {
		languageHandler = lang;
	}
	
	public static boolean isValidMajorNetwork(String majorNetwork) {
		String ip = majorNetwork.split("/")[0];
		int ipValue = 0;
		if (majorNetwork.split("/").length == 2) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetwork") + "<br>"
					+ languageHandler.getKey("word_example").substring(0, 1).toUpperCase() + languageHandler.getKey("word_example").substring(1) + ": 192.168.0.0/24");
			return false;
		}
		if (ip.split("\\.").length != 4) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetwork") + "<br>"
					+ languageHandler.getKey("word_example").substring(0, 1).toUpperCase() + languageHandler.getKey("word_example").substring(1) + ": 192.168.0.0/24");
			return false;
		}
		try {
			int CIDR = Integer.parseInt(majorNetwork.split("/")[1]);
			if (CIDR < 0 || CIDR > 32) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidcidr"));
				return false;
			}
		} catch (NumberFormatException ex) {
			Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidcidrnotation") + "<br>"
					+ languageHandler.getKey("vlsm_error_validcidrnotation"));
			return false;
		}
		for (int i = 0; i < 4; i++) {
			ipValue = Integer.parseInt(ip.split("\\.")[i]);
			if (ipValue < 0 || ipValue > 255) {
				Popup.showErrorMessage(languageHandler.getKey("vlsm_error_invalidmajornetworksegment") + "<br>" 
						+ languageHandler.getKey("vlsm_error_validmajornetworksegment"));
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValidIPv4Address(String IPv4Address) {
		String[] ip = IPv4Address.split("\\.");
		if (ip.length != 4) return false;
		try {
			for (int i = 0; i < 4; i++) {
				if (!(ip[i].length() > 0 && ip[i].length() < 4)) 
					return false;
				if (Integer.parseInt(ip[i]) > 255)
					return false;
				if (Integer.parseInt(ip[i]) < 0)
					return false;
			}
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public static boolean isValidNetmask(String netmask) {
		for (int i = 0; i < KNOWN_NETMASKS.length; i++) {
			if (netmask.equalsIgnoreCase(KNOWN_NETMASKS[i]))
				return true;
		}
		return false;
	}
	
	public static boolean isValidCIDR(int cidr) {
		try {
			if ((cidr >= 0 && cidr <= 32))
				return true;
		} catch (NumberFormatException ex) {
			return false;
		}
		return false;
	}
	
	public static boolean isValidCIDR(String cidr) {
		try {
			return isValidCIDR(Integer.parseInt(cidr));
		} catch (NumberFormatException ex) {
			return false;
		}
		
	}
	
}