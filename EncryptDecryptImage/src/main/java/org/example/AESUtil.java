package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static void saveKey(SecretKey key, String filePath) throws IOException {
        byte[] keyBytes = key.getEncoded();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(keyBytes);
        }
    }

    public static SecretKey loadKey(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.length() == 0) {
            throw new IllegalArgumentException("Key file is empty");
        }
        byte[] keyBytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(keyBytes);
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(byte[] ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciphertext);
    }
}
