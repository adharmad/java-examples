package com.example.javaexamples.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class DBObj {
    @XmlElement(name = "p_credencial", required = true, nillable = true)
    protected String pCredencial;
    @XmlElement(name = "p_cursor", nillable = true)
    protected List<CursorObj> pCursor;

    public DBObj() {
    }

    public String getpCredencial() {
        return pCredencial;
    }

    public void setpCredencial(String pCredencial) {
        this.pCredencial = pCredencial;
    }

    public List<CursorObj> getpCursor() {
        return pCursor;
    }

    public void setpCursor(List<CursorObj> pCursor) {
        this.pCursor = pCursor;
    }

    @Override
    public String toString() {
        return "DBObj{" +
                "pCredencial='" + pCredencial + '\'' +
                ", pCursor=" + pCursor +
                '}';
    }
}
