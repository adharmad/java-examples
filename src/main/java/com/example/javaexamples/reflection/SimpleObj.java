package com.example.javaexamples.reflection;

public class SimpleObj {
    @ObjMeta(columnName="DB_ELEM1")
    private String elem1;
    @ObjMeta(columnName="DB_NUM1")
    private long num1;
    @ObjMeta(columnName="DB_NUM2")
    private int num2;
    @ObjMeta(columnName="DB_BOOL1")
    private boolean bool1;

    public SimpleObj() {
    }

    public String getElem1() {
        return elem1;
    }

    public void setElem1(String elem1) {
        this.elem1 = elem1;
    }

    public long getNum1() {
        return num1;
    }

    public void setNum1(long num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public boolean isBool1() {
        return bool1;
    }

    public void setBool1(boolean bool1) {
        this.bool1 = bool1;
    }

    @Override
    public String toString() {
        return "SimpleObj{" +
                "elem1='" + elem1 + '\'' +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", bool1=" + bool1 +
                '}';
    }
}
