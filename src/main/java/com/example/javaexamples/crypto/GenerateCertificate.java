package com.example.javaexamples.crypto;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class GenerateCertificate {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("/tmp/test.cer");
        BufferedInputStream bis = new BufferedInputStream(fis);

        CertificateFactory cf = CertificateFactory.getInstance("X509");
        X509Certificate c = (X509Certificate) cf.generateCertificate(bis);

        System.out.println("type = " + c.getType());
        System.out.println("version = " + c.getVersion());
        System.out.println("subject = " + c.getSubjectDN().getName());
        System.out.println("valid from = " + c.getNotBefore());
        System.out.println("valid to = " + c.getNotAfter());
        System.out.println("serial number = "
                + c.getSerialNumber().toString(16));
        System.out.println("issuer = " + c.getIssuerDN().getName());
        System.out.println("signing algorithm = " + c.getSigAlgName());
        System.out.println("public key algorithm = "
                + c.getPublicKey().getAlgorithm());

    }
}

