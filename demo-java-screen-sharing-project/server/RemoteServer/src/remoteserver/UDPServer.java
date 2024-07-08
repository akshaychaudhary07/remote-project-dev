package remoteserver;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private static final int SERVER_PORT = 9000; // Choose any port number
    private static final String OUTPUT_FILE = "received_audio.wav"; // Output file name

    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);

        byte[] receiveData = new byte[1024];

        System.out.println("UDP Server listening on port " + SERVER_PORT);

        // Create FileOutputStream to write audio data to file
        FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            // Write received audio data to file
            byte[] audioData = receivePacket.getData();
            int length = receivePacket.getLength();
            outputStream.write(audioData, 0, length);

            System.out.println("Received and saved audio data length: " + length);
        }

        // Close FileOutputStream
//        outputStream.close();
    }
}
