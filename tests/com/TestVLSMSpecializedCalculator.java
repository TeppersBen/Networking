package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.engine.calculators.VLSMSpecializedCalculator;

class TestVLSMSpecializedCalculator {
	
	private final int[] KNOWN_HOSTS = { -1, 0, 2, 6, 14, 30, 62, 126, 254, 510, 1022, 2046, 4094, 8190, 16382, 32766, 65534, 131070, 262142, 524286,
			1048574, 2097150, 4194302, 8388606, 16777214, 33554430, 67108862, 134217726, 268435454, 536870910, 1073741822, 2147483645};
	
	private final int[] REQUESTED_HOSTS = { -1, 0, 1, 4, 10, 25, 58, 120, 250, 480, 860, 1840, 3950, 8000, 15750, 25875, 48722, 100000, 243575, 389854,
			1000000, 2000000, 4000000, 8000000, 16000000, 33000000, 67000000, 130000000, 260000000, 530000000, 1000000000, 2100000000};
	
	
	private final String[] KNOWN_NETMASKS = { "0.0.0.0", "128.0.0.0", "192.0.0.0", "224.0.0.0", "240.0.0.0", "248.0.0.0", "252.0.0.0", "254.0.0.0",
			"255.0.0.0", "255.128.0.0", "255.192.0.0", "255.224.0.0", "255.240.0.0", "255.248.0.0", "255.252.0.0", "255.254.0.0",
			"255.255.0.0", "255.255.128.0", "255.255.192.0", "255.255.224.0", "255.255.240.0", "255.255.248.0", "255.255.252.0", "255.255.254.0",
			"255.255.255.0", "255.255.255.128", "255.255.255.192", "255.255.255.224", "255.255.255.240", "255.255.255.248", "255.255.255.252" ,"255.255.255.254", "255.255.255.255"};
	
	@Test
	void checkKnownHosts() {
		int[] hosts = VLSMSpecializedCalculator.getKnownHosts();
		for (int i = 0; i < hosts.length; i++) {
			if (hosts[i] != KNOWN_HOSTS[i])
				fail("getKnownHosts() returns wrong values!");
		}
	}
	
	@Test
	void checkValidHosts() {
		for (int i = 2; i < KNOWN_HOSTS.length-2; i++) {
			if (KNOWN_HOSTS[i] != Integer.parseInt(VLSMSpecializedCalculator.getValidHost(REQUESTED_HOSTS[i])))
				fail("getValidHost(int) returns wrong values!");
		}
	}

	@Test
	void checkGetCIDR() {
		int value = 0;
		for (int i = 0; i < 32; i++) {
			value = Integer.parseInt(VLSMSpecializedCalculator.getCIDR(KNOWN_HOSTS[i]));
			if (value != (32 - i))
				fail("getCIDR(int) returns wrong values!");
		}
	}
	
	@Test
	void checkGetNetmask() {
		for (int i = 0; i < 32; i++) {
			assertEquals(KNOWN_NETMASKS[(32 - i)], VLSMSpecializedCalculator.getNetmask(REQUESTED_HOSTS[i]));
		}
	}
	
	@Test
	void checkCalculateNetwork() {
		String[] output = VLSMSpecializedCalculator.calculateNetwork("192.168.0.1", 24);
		assertEquals("192.168.0.0", output[0]);
		assertEquals("192.168.0.1", output[1]);
		assertEquals("192.168.0.254", output[2]);
		assertEquals("192.168.0.255", output[3]);
	}
	
	@Test
	void checkIPAdd() {
		assertEquals("192.168.1.0", VLSMSpecializedCalculator.ipAdd("192.168.0.255", 1));
	}
}
