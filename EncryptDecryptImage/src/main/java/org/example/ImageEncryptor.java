package org.example;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageEncryptor {
    private final SecretKey secretKey;

    public ImageEncryptor(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] encryptImage(byte[] imageBytes) throws Exception {
        return AESUtil.encrypt(imageBytes, secretKey);
    }

    public byte[] decryptImage(byte[] encryptedBytes) throws Exception {
        return AESUtil.decrypt(encryptedBytes, secretKey);
    }
}