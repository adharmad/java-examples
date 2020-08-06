package com.example.javaexamples.reflection;

/**
 * @author Amol Dharmadhikari
 */
public class CEO {
    private String first;
    private String last;
    private int salary;
    private  Zoo zoo;

    public CEO() {
        first = "CEOFirst";
        last = "CEOLast";
        salary = 100;

        zoo = new Zoo();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public String toString() {
        return "CEO{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", salary=" + salary +
                ", zoo=" + zoo +
                '}';
    }
}
