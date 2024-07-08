package com.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class SimpleHttpServer {

    private static final int PORT = 80;
    private static final String ADDRESS = "192.168.0.45";

    public static void main(String[] args) {
        try {
            // Create and start the HTTP server
            InetSocketAddress socketAddress = new InetSocketAddress(ADDRESS, PORT);
            HttpServer server = HttpServer.create(socketAddress, 0);

            // Define the handler for the root path "/"
            server.createContext("/", new RootHandler());

            // Start the server
            System.out.printf("Starting server at %s:%d\n", ADDRESS, PORT);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handler for the root path "/"
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello kitty!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}