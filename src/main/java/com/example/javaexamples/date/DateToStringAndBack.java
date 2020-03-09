package com.example.javaexamples.date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringAndBack {
	public static void main(String[] args) throws Exception {
		long cur = System.currentTimeMillis();
		Timestamp ts = new Timestamp(cur);
		String sd = ts.toString();
		System.out.println(sd);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = sdf.parse(sd);
		System.out.println(d.toString());
	}
}
