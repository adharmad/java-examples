package com.example.javaexamples.date;


import java.util.Date;
import java.util.GregorianCalendar;

public class DateSubtract {
	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		gc.add(GregorianCalendar.DAY_OF_YEAR, -30);
		
		System.out.println(gc.getTime());

	}
}
