package com.window.panels.nodes.vlsm;

import com.utils.NetworkConverter;

public class VLSMSpecializedCalculator extends NetworkConverter {
	
	private static int[] knownHosts = getKnownHosts();
	
	public static int[] getKnownHosts() {
		int[] hostSizes = new int[32];
		int mask = 32;
		for (int i = 0; i < hostSizes.length; i++) {
			hostSizes[i] = getTotalValidHosts(mask);
			mask--;
		}
		return hostSizes;
	}
	
	public static String getValidHost(int requestedSize) {
		for (int i = 0; i < knownHosts.length; i++) {
			if (requestedSize - knownHosts[i] >= 0) {
				
			}
		}
		return "";
	}
	
}