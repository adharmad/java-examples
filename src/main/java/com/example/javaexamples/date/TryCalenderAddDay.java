package com.example.javaexamples.date;

import java.util.Calendar;
import java.util.Date;

public class TryCalenderAddDay {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date d1 = new Date(-28800000);
		//c.setTimeInMillis(0);
		c.setTime(d1);
		c.add(Calendar.DAY_OF_MONTH, 31);
		Date d = c.getTime();
		System.out.println(d.toString());
		System.exit(0);
	}
}
