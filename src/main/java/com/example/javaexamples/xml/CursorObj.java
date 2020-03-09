package com.example.javaexamples.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementRef;

public class CursorObj {
    @XmlElementRef(name = "credencial", type = JAXBElement.class, required = false)
    protected JAXBElement<String> credencial;
    @XmlElementRef(name = "emporigem", type = JAXBElement.class, required = false)
    protected JAXBElement<String> emporigem;
    @XmlElementRef(name = "origemcontrato", type = JAXBElement.class, required = false)
    protected JAXBElement<String> origemcontrato;

    public CursorObj() {
    }

    public JAXBElement<String> getCredencial() {
        return credencial;
    }

    public void setCredencial(JAXBElement<String> credencial) {
        this.credencial = credencial;
    }

    public JAXBElement<String> getEmporigem() {
        return emporigem;
    }

    public void setEmporigem(JAXBElement<String> emporigem) {
        this.emporigem = emporigem;
    }

    public JAXBElement<String> getOrigemcontrato() {
        return origemcontrato;
    }

    public void setOrigemcontrato(JAXBElement<String> origemcontrato) {
        this.origemcontrato = origemcontrato;
    }

    @Override
    public String toString() {
        return "CursorObj{" +
                "credencial=" + credencial.getValue() +
                ", emporigem=" + emporigem.getValue() +
                ", origemcontrato=" + origemcontrato.getValue() +
                '}';
    }
}
