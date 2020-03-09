package com.example.javaexamples.xml;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
public class Department {
	private List employees;
	private String name;
	
	public Department(String name){
		this.name = name;
	}
	public List getEmployees() {
		return employees;
	}
	public void setEmployees(List employees) {
		this.employees = employees;
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
