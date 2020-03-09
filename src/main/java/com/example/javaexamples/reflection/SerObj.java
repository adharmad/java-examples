package com.example.javaexamples.reflection;

import java.io.Serializable;

public class SerObj implements Serializable {
	private Object value;
	private int ii;
	private String ss;
	
	public SerObj() {
		StringBuffer buf = new StringBuffer();
		buf.append("hello world");
		value = buf;
		ii = 10;
		ss = "kashmira";
	}
	
	public String toString() {
		return ((StringBuffer)value).toString() + " " + ii + " " + ss;
	}
}
