package com;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.engine.utils.VersionCreator;

class TestVersionCreator {

	@Test
	void checkValidVersionCreated() {
		String actual = new VersionCreator(5, 7, 950).toString();
		LocalDate projectStartDate = LocalDate.of(2017, Month.SEPTEMBER, 28);
		LocalDate currentTime = LocalDate.now();
		int monthsBetween = (int) ChronoUnit.MONTHS.between(projectStartDate, currentTime);
		String expected = String.format("%d.%02d.%02d%02d.%03d", 5, 7, monthsBetween, currentTime.getDayOfMonth(), 950);
		assertEquals(expected, actual);
	}

}
