package com.example.javaexamples.reflection;

public class Zoo {
    private int numAnimals;
    private long numBirds;
    private double numBacteria;
    private String foo;
    private String boo;
    private String too;
    private EmbObj embObj;
    private LstTypeObj lstTypeObj;

    public Zoo() {
        numAnimals = 66;
        numBacteria = 1.1;
        numBirds = 100;
        foo = "hello";
        boo = "world";
        too = "take this";
        embObj = new EmbObj();
        lstTypeObj = new LstTypeObj();
    }

    public EmbObj getEmbObj() {
        return embObj;
    }

    public void setEmbObj(EmbObj embObj) {
        this.embObj = embObj;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBoo() {
        return boo;
    }

    public void setBoo(String boo) {
        this.boo = boo;
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        this.too = too;
    }

    public int getNumAnimals() {
        return numAnimals;
    }

    public void setNumAnimals(int numAnimals) {
        this.numAnimals = numAnimals;
    }

    public long getNumBirds() {
        return numBirds;
    }

    public void setNumBirds(long numBirds) {
        this.numBirds = numBirds;
    }

    public double getNumBacteria() {
        return numBacteria;
    }

    public void setNumBacteria(double numBacteria) {
        this.numBacteria = numBacteria;
    }

    public LstTypeObj getLstTypeObj() {
        return lstTypeObj;
    }

    public void setLstTypeObj(LstTypeObj lstTypeObj) {
        this.lstTypeObj = lstTypeObj;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "numAnimals=" + numAnimals +
                ", numBirds=" + numBirds +
                ", numBacteria=" + numBacteria +
                ", foo='" + foo + '\'' +
                ", boo='" + boo + '\'' +
                ", too='" + too + '\'' +
                ", embObj=" + embObj +
                ", lstTypeObj=" + lstTypeObj +
                '}';
    }
}
