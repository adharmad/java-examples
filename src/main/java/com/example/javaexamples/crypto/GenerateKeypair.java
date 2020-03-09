package com.example.javaexamples.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeypair {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair kp = kpg.generateKeyPair();

        PublicKey pk = kp.getPublic();
        PrivateKey pvk = kp.getPrivate();


        System.out.println("Public Key: ");
        System.out.println("\tAlgorithm: " + pk.getAlgorithm());
        System.out.println("\tFormat: " + pk.getFormat());
        System.out.println("\tString rep: " + pk.toString());

        System.out.println("Private Key: ");

        System.out.println("\tAlgorithm: " + pvk.getAlgorithm());
        System.out.println("\tFormat: " + pvk.getFormat());
        System.out.println("\tString rep: " + pvk.toString());

    }
}
