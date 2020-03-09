package com.example.javaexamples.xml;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
public class Employee {
	private String name;
	private String jobTitle;
	private int age;
	
	public Employee(String name, String jobTitle, int age){
		this.name = name;
		this.jobTitle = jobTitle;
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString(){
		String myInternals = "";
		try{
			Map internals = BeanUtils.describe(this);
			myInternals = internals.toString();
		} catch(Exception e){
			e.printStackTrace();
		}
		return myInternals;
	}
}
