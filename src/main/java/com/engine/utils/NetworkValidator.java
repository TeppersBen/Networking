package com.engine.utils;

public class NetworkValidator {

    /**
     * Check whether the input is binary or decimal.<br>
     * if thrown exception --Input is invalid (nor binary nor decimal)
     * if true --Input is binary
     * if false --Input is decimal
     * @param input
     * @return true if binary, false if decimal, exception if invalid
     * @throws Exception
     */
    public static boolean isBinaryInput(String input) throws Exception {
        String[] segments = input.split("\\.");
        if (segments.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (segments[i].length() <= 3 && segments[i].length() >= 1) {
                    return false;
                } else if (segments[i].length() == 8) {
                    return true;
                } else throw new Exception("Invalid input detected");
            }
            return true;
        } else throw new Exception("Invalid input detected");
    }

}
