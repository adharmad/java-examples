package com.example.javaexamples.reflection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amol Dharmadhikari
 */
public class Company {
    private String name;
    private String regNo;
    private int revenue;
    private CEO ceo;
    private List<Department> departments;

    public Company() {
        name = "foo";
        regNo = "100";
        revenue = 10;

        for (int i=0 ; i< 5 ; i++) {
            Department department = new Department("dept" + i, "country" + i);
            getDepartments().add(department);
        }

        ceo = new CEO();
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


    public CEO getCeo() {
        return ceo;
    }

    public void setCeo(CEO ceo) {
        this.ceo = ceo;
    }

    public List<Department> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<Department>();
        }
        return this.departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", regNo='" + regNo + '\'' +
                ", revenue=" + revenue +
                ", ceo=" + ceo +
                ", departments=" + departments +
                '}';
    }
}
