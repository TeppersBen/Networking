package com.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class VersionCreator {

	private int major;
	private int minor;
	private int bugsFixed;
	
	private LocalDate projectStartDate;
	private LocalDate currentTime;
	
	private int monthsBetween;
	
	public VersionCreator(int major, int minor, int bugsFixed) {
		this.major = major;
		this.minor = minor;
		this.bugsFixed = bugsFixed;
		projectStartDate = LocalDate.of(2017, Month.SEPTEMBER, 28);
		currentTime = LocalDate.now();
		monthsBetween = (int) ChronoUnit.MONTHS.between(projectStartDate, currentTime);
	}
	
	public String toString() {
		return String.format("%d.%02d.%02d%02d.%03d", major, minor, monthsBetween, currentTime.getDayOfMonth(), bugsFixed);
	}
	
}