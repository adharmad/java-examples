package com.example.javaexamples.xml;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementRef;

public class Company {
	private String name;
	private String place;
	private String location;
	private List departmentList;
    @XmlElementRef(name = "p_cod_erro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pCodErro;
	
	public Company(String name){
		this.name = name;
	}
	public List getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List departmentList) {
		this.departmentList = departmentList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public JAXBElement<String> getpCodErro() {
        return pCodErro;
    }

    public void setpCodErro(JAXBElement<String> pCodErro) {
        this.pCodErro = pCodErro;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
