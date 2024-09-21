package com.example.javaexamples.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AESEnc {
    public static void main(String[] args) throws Exception {
        String input = "threequarksformustermark";
        String password = "password";
        String salt = System.currentTimeMillis() + "";
        //SecretKey key = generateKey(256);
        SecretKey key = getKeyFromPassword(password, salt);

        // get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("serialized key | " + encodedKey + " |");

        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        IvParameterSpec ivParameterSpec = generateIvWellKnown();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = encrypt(algorithm, input, key, ivParameterSpec);
        System.out.println(cipherText);

        String plainText = decrypt(algorithm, cipherText, originalKey, ivParameterSpec);
        System.out.println(plainText);
    }

    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec generateIvWellKnown() {
        byte[] iv = new byte[16];
        byte b = Byte.MAX_VALUE;
        for (int i= 0 ; i<iv.length ; i++) {
            while (!isPrime(b)) {
                b--;
            }
            iv[i] = b;
            b--;
        }

        //new SecureRandom().nextBytes(iv);

        for (int i=0 ; i<iv.length ; i++) {
            System.out.print(iv[i] + " ");
        }
        System.out.println();

        return new IvParameterSpec(iv);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {return false;}
        if (n == 2) {return true;}
        if (n % 2 == 0) {return false;}
        for (int i = 3; i <= Math.sqrt(n) + 1; i = i + 2) {
            if (n % i == 0) {return false;}
        }
        return true;
    }
}
