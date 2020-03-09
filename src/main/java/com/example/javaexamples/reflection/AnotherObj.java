package com.example.javaexamples.reflection;

import java.security.SecureRandom;

public class AnotherObj {
    SecureRandom random = new SecureRandom();

    private String aField;
    private String bField;
    private String cField;

    public AnotherObj() {
        byte[] b = new byte[10];
        random.nextBytes(b);
        aField = "aField" + new String(b);
        random.nextBytes(b);
        bField = "bField" + new String(b);
        random.nextBytes(b);
        cField = "cField33" + new String(b);
    }

    public String getaField() {
        return aField;
    }

    public void setaField(String aField) {
        this.aField = aField;
    }

    public String getbField() {
        return bField;
    }

    public void setbField(String bField) {
        this.bField = bField;
    }

    public String getcField() {
        return cField;
    }

    public void setcField(String cField) {
        this.cField = cField;
    }
}
