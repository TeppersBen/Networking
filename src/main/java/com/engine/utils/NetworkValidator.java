package com.engine.utils;

import com.engine.handlers.LanguageHandler;

public class NetworkValidator {

    private static final String[] KNOWN_NETMASKS = { "0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0",
            "255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0",
            "255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0",
            "255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252" ,"255.255.255.254", "255.255.255.255"};

    /**
     * Check whether the input is binary or decimal.
     * if thrown exception --Input is invalid (nor binary nor decimal).
     * if true --Input is binary
     * if false --Input is decimal
     * @param input
     * @return true if binary, false if decimal, exception if invalid
     * @throws Exception Whenever the input is not a decimal nor binary IPv4
     */
    public static boolean isBinaryInput(String input) throws Exception {
        String[] segments = input.split("\\.");
        if (segments.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (segments[i].length() <= 3 && segments[i].length() >= 1) {
                    return false;
                } else if (segments[i].length() == 8) {
                    return true;
                } else throw new Exception(LanguageHandler.getKey("converter_IPv4_error_invalidIPv4Address"));
            }
            return true;
        } else throw new Exception(LanguageHandler.getKey("converter_IPv4_error_invalidIPv4Address"));
    }

    /**
     * Checks if the netmask decimal notation is valid.<br>
     * I.E.: <br><code>
     *     255.255.128.0 -> true (valid)<br>
     * 	   257.287.024.253 -> false (invalid)
     * </code>
     * @param netmask String
     * @return true if netmask is valid.
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
     * Checks whether the input is a CIDR notation or a regular netmask notation.<br>
     * if CIDR > true<br>
     * if regular netmask notation > false<br>
     * if invalid > Exception<br>
     * @param input String
     * @return true if CIDR<br>false if regular netmask notation<br>Exception if invalid
     * @throws Exception whenever the input is not a CIDR- nor regular notation.
     */
    public static boolean isCIDRNotation(String input) throws Exception {
        if (input.length() == 1 || input.length() == 2) {
            if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 24) {
                return true;
            }
        } else if (input.split("\\.").length == 4) {
            if (isValidNetmask(input)) {
                return false;
            } else {
                throw new Exception("Invalid input detected");
            }
        }
        throw new Exception("Invalid input detected");
    }

}
