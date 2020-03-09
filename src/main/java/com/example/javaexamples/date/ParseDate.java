package com.example.javaexamples.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		//String s = sdf.format(new Date());
		Date d = sdf.parse("2006/05/01 00:00:00");
		System.out.println(d);
	}
}
