package com.example.javaexamples.crypto;

import org.apache.commons.lang3.tuple.Pair;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricEnc {
    public static final int CRITICAL_SIZE = 128;
    public static void main(String[] args) throws Exception {
        String input = "threequarksformustermark";
        //String input = new String(Files.readAllBytes(Paths.get("/Users/amol/soteria_schema.sql")));

        Pair<PublicKey, PrivateKey> pair = generateKeyPair(1024);
        PublicKey publicKey = pair.getKey();
        PrivateKey privateKey = pair.getValue();

        // get base64 encoded version of the keys
        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("serialized public key | " + encodedPublicKey + " |");

        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        System.out.println("serialized private key | " + encodedPrivateKey + " |");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

//        checkPublicKey(keyFactory, publicKey, encodedPublicKey);
//        checkPrivateKey(keyFactory, privateKey, encodedPrivateKey);

        String algorithm = "RSA/ECB/PKCS1Padding";
        String cipherText = encrypt(algorithm, input, publicKey);
        System.out.println("========================================================================================");
        System.out.println(cipherText);
        System.out.println("========================================================================================");

        String plainText = decrypt(algorithm, cipherText, privateKey);
        System.out.println(plainText);
        System.out.println("========================================================================================");
    }

    public static String encrypt(String algorithm, String input, PublicKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = null;
        if (input.length() <= CRITICAL_SIZE) {
            cipherText = cipher.doFinal(input.getBytes());
        } else {
            cipherText = blockCipher(cipher, input.getBytes(), Cipher.ENCRYPT_MODE);
        }

        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = null;
        if (cipherText.length() <= CRITICAL_SIZE) {
            plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        } else {
            plainText = blockCipher(cipher, Base64.getDecoder().decode(cipherText), Cipher.DECRYPT_MODE);
        }

        return new String(plainText);
    }


    private static byte[] blockCipher(Cipher cipher, byte[] bytes, int mode) throws IllegalBlockSizeException, BadPaddingException{
        // string initialize 2 buffers.
        // scrambled will hold intermediate results
        byte[] scrambled = new byte[0];

        // toReturn will hold the total result
        byte[] toReturn = new byte[0];
        // if we encrypt we use 100 byte long blocks. Decryption requires 128 byte long blocks (because of RSA)
        int length = (mode == Cipher.ENCRYPT_MODE)? 100 : 128;

        // another buffer. this one will hold the bytes that have to be modified in this step
        byte[] buffer = new byte[length];

        for (int i=0; i< bytes.length; i++){

            // if we filled our buffer array we have our block ready for de- or encryption
            if ((i > 0) && (i % length == 0)){
                //execute the operation
                scrambled = cipher.doFinal(buffer);
                // add the result to our total result.
                toReturn = append(toReturn,scrambled);
                // here we calculate the length of the next buffer required
                int newlength = length;

                // if newlength would be longer than remaining bytes in the bytes array we shorten it.
                if (i + length > bytes.length) {
                    newlength = bytes.length - i;
                }
                // clean the buffer array
                buffer = new byte[newlength];
            }
            // copy byte into our buffer.
            buffer[i%length] = bytes[i];
        }

        // this step is needed if we had a trailing buffer. should only happen when encrypting.
        // example: we encrypt 110 bytes. 100 bytes per run means we "forgot" the last 10 bytes. they are in the buffer array
        scrambled = cipher.doFinal(buffer);

        // final step before we can return the modified data.
        toReturn = append(toReturn,scrambled);

        return toReturn;
    }

    private static byte[] append(byte[] prefix, byte[] suffix){
        byte[] toReturn = new byte[prefix.length + suffix.length];
        for (int i=0; i< prefix.length; i++){
            toReturn[i] = prefix[i];
        }
        for (int i=0; i< suffix.length; i++){
            toReturn[i+prefix.length] = suffix[i];
        }
        return toReturn;
    }


    public static Pair<PublicKey, PrivateKey> generateKeyPair(int n) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(n, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        return Pair.of(pub, priv);
    }

    private static void checkPublicKey(KeyFactory keyFactory, PublicKey publicKey, String encodedPublicKey) throws InvalidKeySpecException {
        // decode the base64 encoded public string
        byte[] decodedPublicKey = Base64.getDecoder().decode(encodedPublicKey);
        // rebuild key using X509EncodedKeySpec
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(decodedPublicKey);
        PublicKey origPublicKey = keyFactory.generatePublic(pubKeySpec);

        if (publicKey.equals(origPublicKey)) {
            System.out.println("original and decoded public keys match");
        }
    }

    private static void checkPrivateKey(KeyFactory keyFactory, PrivateKey privateKey, String encodedPrivateKey) throws InvalidKeySpecException {
        // decode the base64 encoded public string
        byte[] decodedPrivateKey = Base64.getDecoder().decode(encodedPrivateKey);
        // rebuild key using PKCS8EncodedKeySpec
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        PrivateKey origPrivKey = keyFactory.generatePrivate(privKeySpec);

        if (privateKey.equals(origPrivKey)) {
            System.out.println("original and decoded private keys match");
        }
    }
}
