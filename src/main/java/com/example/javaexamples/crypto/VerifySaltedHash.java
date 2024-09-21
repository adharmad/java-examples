package com.example.javaexamples.crypto;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class VerifySaltedHash {
    public static void main(String[] args) throws Exception {
        String hashWithSalt = "ce44d03f37cef5b51b8925e3bba5754f:2f722a97b474fdb8936d39b66a69aa05b9d2600a99ee182c2086286256fbc704d92944b0fde234a406e4e450b1aa0e71eea3457e08743a0a59c7e16f269d63f7";
        String password = "hello_world";
        String[] split = hashWithSalt.split(":");
        String saltAsStr = split[0];
        String passwordHash = split[1];

        byte[] salt = Hex.decodeHex(saltAsStr.toCharArray());

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] b = factory.generateSecret(spec).getEncoded();
        String computedPasswordHash = new String(Hex.encodeHex(b));

        System.out.println(passwordHash);
        System.out.println(computedPasswordHash);
    }
}
