package com.example.javaexamples.crypto;

import java.io.BufferedInputStream;
import java.util.Set;
import java.util.Iterator;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * The X.509 format in detail
 * The International Telecommunication Union (ITU) developed and published
 * the X.509 certificate format, which was selected by the Public Key
 * Infrastructure X.509 (PKIX) working group of the Internet Engineering Task
 * Force (IETF).
 * <p>
 * Using a notation called ASN.1 (Abstract Syntax Notation One), the X.509
 * standard defines a certificate's format. ASN.1 is a standardized language
 * that describes abstract data types in a platform-independent manner.
 * The "Internet X.509 Public Key Infrastructure -- Certificate and CRL
 * Profile" document (see Resources for a link) published by the PKIX working
 * group describes an X.509 certificate format in terms of ASN.1 notation.
 * A data type -- such as a certificate -- defined in ASN.1 isn't useful until
 * it can unambiguously define how to represent an instance of a data type
 * as a series of bits. To give the data type that functionality, ASN.1 uses
 * the Distinguished Encoding Rules (DER), which define how to uniquely encode
 * any ASN.1 object.
 * <p>
 * With a copy of an X.509 certificate's ASN.1 definition and a knowledge of
 * the DER, you can write a Java application that will read and write X.509
 * certificates and interoperate with similar applications written in other
 * programming languages. Luckily, you will probably never have to go to that
 * much trouble because the Java 2 Platform, Standard Edition (J2SE) comes
 * with built-in support for X.509 certificates.
 */


public class DumpCertificateInfo {

    public static void main(String[] args) throws Exception {
        // The file specified must contain a single
        // DER-encoded X.509 certificate. The DER-encoded certificate
        // can be in either binary or ASCII format.

        FileInputStream fis = new FileInputStream("C:\\Users\\adharmad.ORADEV\\tmp\\test.cer");
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Get the correct certificate factory.
        CertificateFactory certFactory = CertificateFactory
                .getInstance("X.509");

        // Generate a certificate from the data in the file.
        X509Certificate x509certificate = (X509Certificate) certFactory
                .generateCertificate(bis);

        // First, let's print out information about the certificate
        // itself.
        System.out.println("---Certificate---");
        System.out.println("type = " + x509certificate.getType());
        System.out.println("version = " + x509certificate.getVersion());
        System.out.println("subject = "
                + x509certificate.getSubjectDN().getName());
        System.out.println("valid from = "
                + x509certificate.getNotBefore());
        System.out.println("valid to = "
                + x509certificate.getNotAfter());
        System.out.println("serial number = "
                + x509certificate.getSerialNumber().toString(16));
        System.out.println("issuer = "
                + x509certificate.getIssuerDN().getName());
        System.out.println("signing algorithm = "
                + x509certificate.getSigAlgName());
        System.out.println("public key algorithm = "
                + x509certificate.getPublicKey().getAlgorithm());

        //              // Next, let's print out information about the extensions.
        System.out.println("---Extensions---");
        Set setCritical = x509certificate.getCriticalExtensionOIDs();
        if (setCritical != null && setCritical.isEmpty() == false) {
            for (Iterator iterator = setCritical.iterator(); iterator
                    .hasNext(); ) {
                System.out.println(iterator.next().toString()
                        + " *critical*");
            }

        }
        Set setNonCritical = x509certificate
                .getNonCriticalExtensionOIDs();
        if (setNonCritical != null && setNonCritical.isEmpty() == false)
            for (Iterator iterator = setNonCritical.iterator(); iterator
                    .hasNext(); )
                System.out.println(iterator.next().toString());

        // We're done.
        System.out.println("---");

        // Close the file.
        bis.close();

    }

}

