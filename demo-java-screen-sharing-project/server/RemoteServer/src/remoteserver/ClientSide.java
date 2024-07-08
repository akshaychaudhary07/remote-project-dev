package remoteserver;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ClientSide {
    public static void main(String[] args) throws AWTException {
        String serverAddress = "192.168.0.45";
        int serverPort = 8086;

        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to server at " + serverAddress + ":" + serverPort);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            Thread screenCaptureThread = new Thread(() -> {
                try {
                    while (true) {
                        sendScreenCapture(robot, screenRect, dataOutputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            screenCaptureThread.start();

            while (true) {
                String command = dataInputStream.readUTF();
                handleControlCommand(command, robot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendScreenCapture(Robot robot, Rectangle screenRect, DataOutputStream dataOutputStream) throws IOException {
        BufferedImage screenCapture = robot.createScreenCapture(screenRect);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(screenCapture, "jpeg", byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        dataOutputStream.writeInt(imageBytes.length);
        dataOutputStream.write(imageBytes);
        dataOutputStream.flush();
    }

    private static void handleControlCommand(String command, Robot robot) {
        String[] parts = command.split(",");
        String action = parts[0];

        try {
            switch (action) {
                case "MOVE":
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    robot.mouseMove(x, y);
                    break;
                case "CLICK":
                    int button = Integer.parseInt(parts[1]);
                    robot.mousePress(InputEvent.getMaskForButton(button));
                    robot.mouseRelease(InputEvent.getMaskForButton(button));
                    break;
                case "KEYPRESS":
                    int keyCode = Integer.parseInt(parts[1]);

                    SwingUtilities.invokeLater(() -> {
                        robot.keyPress(keyCode);
                        robot.keyRelease(keyCode);
                    });
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}