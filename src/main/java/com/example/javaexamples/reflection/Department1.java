package com.example.javaexamples.reflection;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class Department1 {
    private String deptName;
    private String deptCountry;

    public Department1() {
    }

    public Department1(String deptName, String deptCountry) {
        this.deptName = deptName;
        this.deptCountry = deptCountry;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCountry() {
        return deptCountry;
    }

    public void setDeptCountry(String deptCountry) {
        this.deptCountry = deptCountry;
    }

    @Override
    public String toString() {
        return "Department1{" +
                "deptName='" + deptName + '\'' +
                ", deptCountry='" + deptCountry + '\'' +
                '}';
    }
}
