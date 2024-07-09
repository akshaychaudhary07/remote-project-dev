package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Base64;

public class SimpleHttpServer {
    private static final String KEY_FILE_PATH = "src/main/resources/key/aes.key";
    private static SecretKey secretKey;
    public static void main(String[] args) throws Exception {
        File keyFile = new File(KEY_FILE_PATH);
        if (!keyFile.exists() || keyFile.length() == 0) {
            secretKey = AESUtil.generateKey(256);
            AESUtil.saveKey(secretKey, KEY_FILE_PATH);
            System.out.println("Key generated and saved.");
        } else {
            secretKey = AESUtil.loadKey(KEY_FILE_PATH);
            System.out.println("Key loaded.");
        }
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/encrypt", new EncryptHandler());
        server.createContext("/decrypt", new DecryptHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080...");
    }

    static class EncryptHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                byte[] imageBytes = is.readAllBytes();

                try {
                    byte[] encryptedBytes = AESUtil.encrypt(imageBytes, secretKey);
                    String base64Encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
                    exchange.sendResponseHeaders(200, base64Encrypted.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(base64Encrypted.getBytes());
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, -1);
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
            exchange.close();
        }
    }
    static class DecryptHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {

                InputStream is = exchange.getRequestBody();
                String base64Encrypted = new String(is.readAllBytes());
                byte[] encryptedBytes = Base64.getDecoder().decode(base64Encrypted);
                try {
                    byte[] decryptedBytes = AESUtil.decrypt(encryptedBytes, secretKey);
                    exchange.sendResponseHeaders(200, decryptedBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(decryptedBytes);
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    exchange.sendResponseHeaders(500, -1);
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
            exchange.close();
        }
    }
}