package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.engine.calculators.Convert;

class TestConvert {

	@Test
	void checkBinaryToDecimal() {
		assertEquals("192", Convert.binaryToDecimal("11000000"));
		assertEquals("1", Convert.binaryToDecimal("00000001"));
	}

	@Test
	void checkDecimalToBinary() {
		assertEquals("11000000", Convert.decimalToBinary(192));
		assertEquals("00000001", Convert.decimalToBinary(1));
	}

	@Test
	void checkCreateIPv4BitList() {
		List<Integer> list = Convert.createIPv4BitList();
		final int[] bits = { 128, 64, 32, 16, 8, 4, 2, 1 };
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != bits[i])
				fail("createIPv4BitList() returns wrong values!");
		}
	}

	@Test
	void checkCreateMaxBitList() {
		List<Integer> list = Convert.createMaxBitList();
		final int[] bits = { 15, 7, 3, 1 };
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != bits[i])
				fail("createMaxBitList() returns wrong values!");
		}
	}

}