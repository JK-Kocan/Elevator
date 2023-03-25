import javax.swing.*;
import java.awt.*;

public class ElevatorGUI extends JFrame {
    
    private JPanel elevatorPanel1, elevatorPanel2, elevatorPanel3;
    private JPanel buttonPanel1, buttonPanel2, buttonPanel3;
    private JButton[] buttonArray1, buttonArray2, buttonArray3;
    private JLabel elevatorLabel1, elevatorLabel2, elevatorLabel3;
    
    public ElevatorGUI() {
        setTitle("Elevator Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // Initialize elevator panels
        elevatorPanel1 = new JPanel();
        
        // Set layout of elevator panels
        elevatorPanel1.setLayout(new BorderLayout());
        
        // Initialize button panels
        
        // Set layout of button panels
        buttonPanel1.setLayout(new GridLayout(5, 3));
        buttonPanel1.setSize(30,50);
        buttonPanel2.setLayout(new GridLayout(5, 3));
        buttonPanel2.setSize(30,50);
        buttonPanel3.setLayout(new GridLayout(5, 3));
        buttonPanel3.setSize(30,50);
        
        // Initialize button arrays
        buttonArray1 = new JButton[15];
        buttonArray2 = new JButton[15];
        buttonArray3 = new JButton[15];
        
        // Initialize elevator labels
        elevatorLabel1 = new JLabel("Elevator 1", SwingConstants.CENTER);
        elevatorLabel2 = new JLabel("Elevator 2", SwingConstants.CENTER);
        elevatorLabel3 = new JLabel("Elevator 3", SwingConstants.CENTER);
        
        // Add elevator labels to elevator panels
        elevatorPanel1.add(elevatorLabel1, BorderLayout.NORTH);
        elevatorPanel2.add(elevatorLabel2, BorderLayout.NORTH);
        elevatorPanel3.add(elevatorLabel3, BorderLayout.NORTH);
        
        // Add button arrays to button panels
        for (int i = 0; i < 15; i++) {
            buttonArray1[i] = new JButton("" + (i+1));
            buttonArray2[i] = new JButton("" + (i+1));
            buttonArray3[i] = new JButton("" + (i+1));
            buttonPanel1.add(buttonArray1[i]);
            buttonPanel2.add(buttonArray2[i]);
            buttonPanel3.add(buttonArray3[i]);
        }
        
        // Add button panels to elevator panels
        elevatorPanel1.add(buttonPanel1, BorderLayout.CENTER);
        elevatorPanel2.add(buttonPanel2, BorderLayout.CENTER);
        elevatorPanel3.add(buttonPanel3, BorderLayout.CENTER);
        
        // Set the size and position of the button panels
        buttonPanel1.setPreferredSize(new Dimension(150, 150));
        buttonPanel2.setPreferredSize(new Dimension(150, 150));
        buttonPanel3.setPreferredSize(new Dimension(150, 150));
        
        // Add the elevator panels to the main panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(elevatorPanel1);
        mainPanel.add(elevatorPanel2);
        mainPanel.add(elevatorPanel3);
        add(mainPanel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new ElevatorGUI();
    }
}
