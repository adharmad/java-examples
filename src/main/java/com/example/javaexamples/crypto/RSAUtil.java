package com.example.javaexamples.crypto;

import javax.crypto.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RSAUtil {
    public static void main(String[] args) throws Exception{
        String input2 = "hello world";
        String input1 = """
                   * >Natural Source Header 000000
                * :Mode S
                * :CP IBM01140
                * <Natural Source Header
                * MAP2: PROTOTYPE VERSION 820 --- CREATED BY NAT 0901020013 ---
                * INPUT USING MAP 'XXXXXXXX'
                *     #CV-INSCRICAO #CV-NI #LABEL-INSCRICAO #SN M50801.IT-INdddddd
                """;

        String input = """
                * >Natural Source Header 000000
                * :Mode S
                * :CP IBM01140
                * <Natural Source Header
                * MAP2: PROTOTYPE VERSION 820 --- CREATED BY NAT 0901020013 ---
                * INPUT USING MAP 'XXXXXXXX'
                *     #CV-INSCRICAO #CV-NI #LABEL-INSCRICAO #SN M50801.IT-IN-APOSENTADO
                *     M50801.IT-NI-PROPRIETARIO M50801.IT-NO-BAIRRO
                *     M50801.IT-NO-LOGRADOURO M50801.IT-NO-PROPRIETARIO
                *     M50801.IT-NU-CEP M50801.IT-NU-INSCRICAO M50801.IT-NU-LOGRADOURO
                *     M50801.IT-NU-TELEFONE(*) PARAMETROS.IT-ANO-REFERENCIA
                *     PARAMETROS.IT-TX-MEMORIA-CALCULO
                DEFINE DATA PARAMETER
              
               """;

        String privateKeyPem = readFile("certs/private_key_rsa_4096_pkcs8.pem")  ;
        String publicKeyPem = readFile("certs/public_key_rsa_4096_pkcs8.pem")  ;

        PublicKey publicKey = getPublicKey(publicKeyPem, true);
        PrivateKey privateKey = getPrivateKey(privateKeyPem, true);

        String algorithm = "RSA/ECB/PKCS1Padding";
        String cipherText =  encrypt(algorithm, input2, publicKey);

        System.out.println("=".repeat(35));
        System.out.println("Using public key: " + cipherText);
        System.out.println("=".repeat(35));

        String plainText =  decrypt(algorithm, cipherText, privateKey);
        System.out.println("Using private key: " + plainText);
        System.out.println("=".repeat(35));
    }

    public static String encrypt(String input, String publicKeyPem) throws Exception{
        return encrypt(input,publicKeyPem,true);
    }
    public static String encrypt(String input, String publicKeyPem, boolean isPem) throws Exception{
        PublicKey publicKey = getPublicKey(publicKeyPem, isPem);
        String algorithm = "RSA/ECB/PKCS1Padding";
        return encrypt(algorithm, input, publicKey);
    }

    private static String readFile(final String fileName) throws IOException {
        final File file = new File(RSAUtil.class.getClassLoader().getResource(fileName).getFile());
        List<String> lines = Files.readAllLines(Paths.get(file.toPath().toString()));
        String contents = IntStream.range(1, lines.size()-1).mapToObj(i -> lines.get(i)).collect(Collectors.joining());

        // return new String(Files.readAllBytes(file.toPath()));
        return contents;
    }

    public static String encrypt(String algorithm, String input, PublicKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = blockCipher(cipher, input.getBytes(), Cipher.ENCRYPT_MODE);
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = blockCipher(cipher, Base64.getDecoder().decode(cipherText), Cipher.DECRYPT_MODE);
        return new String(plainText);
    }


    private static byte[] blockCipher(Cipher cipher, byte[] input, int mode) throws IllegalBlockSizeException, BadPaddingException{
        byte[] tmp = new byte[0];

        // toReturn will hold the total result
        byte[] toReturn = new byte[0];

        // if we encrypt we use 100 byte long blocks. Decryption requires 128 byte long blocks (because of RSA)
        //int length = (mode == Cipher.ENCRYPT_MODE)? 470 : 512;
        int bufSize = (mode == Cipher.ENCRYPT_MODE)? 501 : 512;

        // another buffer. this one will hold the bytes that have to be modified in this step
        byte[] buffer = new byte[bufSize];

        if (input.length <= bufSize) {
            System.arraycopy(input, 0, buffer, 0, input.length);
            tmp = cipher.doFinal(buffer, 0, input.length);
            toReturn = append(toReturn, tmp);
        } else {
            for (int i=0; i< input.length; i+=bufSize){
                int bytesToCopy = (i + bufSize > input.length) ? input.length - i : bufSize;
                buffer = new byte[bytesToCopy];
                System.arraycopy(input, i, buffer, 0, bytesToCopy);

                tmp = cipher.doFinal(buffer, 0, bytesToCopy);
                toReturn = append(toReturn, tmp);
            }
        }

        return toReturn;
    }

    private static byte[] append(byte[] prefix, byte[] suffix){
        byte[] toReturn = new byte[prefix.length + suffix.length];
        System.arraycopy(prefix, 0, toReturn, 0, prefix.length);
        System.arraycopy(suffix, 0, toReturn, prefix.length, suffix.length);
        return toReturn;
    }


    public static PublicKey getPublicKey (String publicKeyPem, boolean isPem) {
        PublicKey publicKey = null;
        String keyString = isPem ? publicKeyPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "") : publicKeyPem;

        byte[] b64DecodedKeyData = Base64.getDecoder().decode(keyString);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b64DecodedKeyData);
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return publicKey;
    }

    public static PrivateKey getPrivateKey (String keyPem, boolean isPem) {
        PrivateKey privateKey;
        String keyString = isPem ? keyPem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "") : keyPem;

        byte[] b64DecodedKeyData = Base64.getDecoder().decode(keyString);

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b64DecodedKeyData);
            privateKey = keyFactory.generatePrivate(keySpec);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return privateKey;
    }
}
