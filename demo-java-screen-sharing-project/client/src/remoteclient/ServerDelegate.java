package remoteclient;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * Used to receive server commands then execute them at the client side
 */
class ServerDelegate extends Thread {

    Socket socket = null;
    Robot robot = null;
    boolean continueLoop = true;

    public ServerDelegate(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start(); //Start the thread and hence calling run method
    }

    public void run(){
        Scanner scanner = null;
        try {
            //prepare Scanner object
            System.out.println("Preparing InputStream");
            scanner = new Scanner(socket.getInputStream());

            while(continueLoop){
                //receive commands and respond accordingly
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);
                switch(command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                        break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                        break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                        break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                        break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                        break;
                    case -6: // Handle mouse drag
                        int x1 = scanner.nextInt();
                        int y1 = scanner.nextInt();
                        int x2 = scanner.nextInt();
                        int y2 = scanner.nextInt();
                        robot.mouseMove(x1, y1);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseMove(x2, y2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}