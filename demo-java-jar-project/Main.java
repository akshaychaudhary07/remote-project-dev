import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Prompt Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add a label with some text
        JLabel label = new JLabel("Hello, this is a prompt window!", JLabel.CENTER);
        frame.getContentPane().add(label);

        // Set the frame size
        frame.setSize(300, 200);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }
}
