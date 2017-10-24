package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.utils.NetworkConverter;

class TestNetworkConverter {

	@Test
	void checkGetNetmaskClass() {
		assertEquals("A", NetworkConverter.getNetmaskClass("255.0.0.0"));
		assertEquals("B", NetworkConverter.getNetmaskClass("255.255.0.0"));
		assertEquals("C", NetworkConverter.getNetmaskClass("255.255.255.0"));
		assertEquals("D", NetworkConverter.getNetmaskClass("255.255.255.255"));
		assertEquals("/", NetworkConverter.getNetmaskClass("0.0.0.0"));
	}

	@Test
	void checkDecimalIPv4ToBinary() {
		assertEquals("11000000.10101000.00000000.00000001", NetworkConverter.decimalIPv4ToBinary("192.168.0.1"));
	}

	@Test
	void checkBinaryIPv4ToDecimal() {
		assertEquals("192.168.0.1", NetworkConverter.binaryIPv4ToDecimal("11000000.10101000.00000000.00000001"));
	}

	@Test
	void checkNetmaskIntegerToIP() {
		assertEquals("255.255.0.0", NetworkConverter.netmaskIntegerToIP(16));
	}

	@Test
	void checkGetTotalValidHosts() {
		assertEquals(2046, NetworkConverter.getTotalValidHosts(21));
	}

	@Test
	void checkgetTotalValidSubnets() {
		assertEquals(32, NetworkConverter.getTotalValidSubnets(21));
	}

	@Test
	void checkNetmaskDecimalToCIDR() {
		assertEquals("16", NetworkConverter.netmaskDecimalToCIDR("255.255.0.0"));
	}

	@Test
	void checkOperandAND() {
		final String a = "11010010.11011111.00110100.11001000";
		final String b = "11110111.11111111.01110111.11011010";
		final String c = "11010010.11011111.00110100.11001000";
		assertEquals(c, NetworkConverter.operatorAND(a, b));
	}
	
}