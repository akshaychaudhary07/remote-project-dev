package org.example.encryption;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        SecretKey key = AESGCMEncryptor.generateKey();
        byte[] iv = AESGCMEncryptor.generateIV();

        System.out.println("Enter the text to encrypt:");
        String originalText = scanner.nextLine();

        String encryptedText = AESGCMEncryptor.encrypt(originalText, key, iv);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = AESGCMEncryptor.decrypt(encryptedText, key, iv);
        System.out.println("Decrypted: " + decryptedText);

         scanner.close();
    }
}
