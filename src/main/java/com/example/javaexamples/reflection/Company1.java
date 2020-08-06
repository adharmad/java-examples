package com.example.javaexamples.reflection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amol Dharmadhikari
 */
public class Company1 {
    private String name;
    private String regNo;
    private int revenue;
    private CEO1 ceo;
    private List<Department1> departments;

    public Company1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }


    public CEO1 getCeo() {
        return ceo;
    }

    public void setCeo(CEO1 ceo) {
        this.ceo = ceo;
    }

    public List<Department1> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<Department1>();
        }
        return this.departments;
    }

    @Override
    public String toString() {
        return "Company1{" +
                "name='" + name + '\'' +
                ", regNo='" + regNo + '\'' +
                ", revenue=" + revenue +
                ", ceo=" + ceo +
                ", departments=" + departments +
                '}';
    }
}
