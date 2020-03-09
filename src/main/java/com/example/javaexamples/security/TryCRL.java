package com.example.javaexamples.security;

/**
 * TryCRL.java
 * Created on Apr 30, 2005
 * @author Amol Dharmadhikari <amol@dharmadhikari.org>
 * A CRL is literally a digitally signed list that contains the serial numbers and associated 
 * data (such as the revocation date) of public-key certificates that were issued and subsequently 
 * revoked by the CA. When published by a CA, a CRL is placed in a public repository or otherwise 
 * made widely available so that applications can check the revocation status of certificates before 
 * accepting them. 
 * 
 * RFC 2459, published by the Public Key Infrastructure X.509 (PKIX) working group of the Internet 
 * Engineering Task Force (IETF), defines the format of X.509 CRLs. Like X.509 certificates, the 
 * format of an X.509 CRL is specified in ASN.1 (Abstract Syntax Notation One) notation. The ASN.1 
 * Distinguished Encoding Rules (DER) defines a platform-independent binary format.
 * 
 */
import java.io.FileInputStream;
import java.util.Set;
import java.util.Iterator;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;

public class TryCRL {
    public static void main(String[] arstring) {
        try {
            // Get the correct certificate factory.
            CertificateFactory certificatefactory = CertificateFactory
                    .getInstance("X.509");
            // Each file specified on the command line must contain a single
            // DER-encoded X.509 CRL.
            for (int i = 0; i < arstring.length; i++) {
                // Open the file.
                FileInputStream fileinputstream = new FileInputStream(
                        arstring[i]);
                // Generate a certificate from the data in the file.
                X509CRL x509crl = (X509CRL) certificatefactory
                        .generateCRL(fileinputstream);
                // Print out information about the crl.
                System.out.println("---CRL---");
                System.out.println("type = " + x509crl.getType());
                System.out.println("version = " + x509crl.getVersion());
                System.out.println("issuer = "
                        + x509crl.getIssuerDN().getName());
                System.out.println("signing algorithm = "
                        + x509crl.getSigAlgName());
                System.out.println("signing OID = " + x509crl.getSigAlgOID());
                System.out.println("this update = " + x509crl.getThisUpdate());
                System.out.println("next update = " + x509crl.getNextUpdate());
                System.out.println();
                // Next, let's print out information about the entries.
                System.out.println("---Entries---");
                Set setEntries = x509crl.getRevokedCertificates();
                if (setEntries != null && setEntries.isEmpty() == false) {
                    for (Iterator iterator = setEntries.iterator(); iterator
                            .hasNext();) {
                        X509CRLEntry x509crlentry = (X509CRLEntry) iterator
                                .next();
                        System.out.println("serial number = "
                                + x509crlentry.getSerialNumber());
                        System.out.println("revocation date = "
                                + x509crlentry.getRevocationDate());
                        System.out.println("extensions = "
                                + x509crlentry.hasExtensions());
                        System.out.println();
                    }
                }
                // We're done.
                System.out.println("---");
                // Close the file.
                fileinputstream.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}