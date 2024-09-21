package com.example.javaexamples.crypto;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class SaltedHash {
    public static void main(String[] args) throws Exception {
        String password = "hello_world";
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] b = factory.generateSecret(spec).getEncoded();
        String hash = new String(Hex.encodeHex(b));
        String saltStr = new String(Hex.encodeHex(salt));

        hash = String.format("%s:%s", saltStr, new String(Hex.encodeHex(b)));
        System.out.println(hash);
        System.out.println(getSHA512Hash(password, new String(salt)));
    }

    public static String getSHA512Hash(String message, String salt) throws Exception {
        String hash = null;

        /* get SHA-512 instance */
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        /* Add salt */
        md.update(salt.getBytes("UTF-8"));
        /* Get the message digest in bytes */
        byte[] bytes = md.digest(message.getBytes("UTF-8"));
        /* get string out of the bytes using commons codecs  */
        hash = new String(Hex.encodeHex(bytes));

        return hash;
    }
}
