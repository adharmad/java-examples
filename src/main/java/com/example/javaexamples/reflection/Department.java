package com.example.javaexamples.reflection;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class Department {
    private String deptName;
    private String deptCountry;

    public Department() {
        deptName = "hello";
        deptCountry = "US";
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
        return "Department{" +
                "deptName='" + deptName + '\'' +
                ", deptCountry='" + deptCountry + '\'' +
                '}';
    }
}
