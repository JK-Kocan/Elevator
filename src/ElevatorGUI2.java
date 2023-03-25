import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class ElevatorGUI2 extends JFrame {
    private JPanel mainPanel;
    private JPanel elevator1Panel;
    private Queue<String> buttonQueue = new LinkedList<>();
    Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();

    public ElevatorGUI2() {
        setTitle("Elevator GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Create main panel and add elevator panels
        mainPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        elevator1Panel = createElevatorPanel("Elevator 1");
        mainPanel.add(elevator1Panel);
    
        add(mainPanel);
        setVisible(true);

    }
    
    // Creates a panel for an elevator with button panel and floor label
    private JPanel createElevatorPanel(String elevatorName) {
        JPanel elevatorPanel = new JPanel(new BorderLayout());
        elevatorPanel.setBorder(BorderFactory.createTitledBorder(elevatorName));
    
        // Create button panel and add buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3, 5, 3));
        int startNumber = 13;
        int[] countSet = {13, 14, 15};
        int[] buttonSet = new int[15];
        int j = 0;
        for (int i = startNumber; i >= 0; i--) {
            int numIndex = (startNumber - i) % 3;
            int countNumber = countSet[numIndex] - (startNumber - i) / 3 * 3;
            buttonSet[j] = countNumber;
            dictionary.put(countNumber, j);
            j++;
            if (countNumber == 2) {
                countNumber = 3;
                buttonSet[j] = countNumber;
                dictionary.put(countNumber, j);
            }
        }

        // Create floor label and add to panel
        JLabel floorLabel = new JLabel("Floor: 1");
        floorLabel.setHorizontalAlignment(JLabel.CENTER);
        elevatorPanel.add(floorLabel, BorderLayout.NORTH);

        for (int i = 0; i < buttonSet.length; i++) {
            String floor = Integer.toString(buttonSet[i]);
            JToggleButton button = new JToggleButton(floor);
            button.setPreferredSize(new Dimension(40, 10));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if ( ((JToggleButton)e.getSource()).getText().equals(floorLabel.getText().substring(7)) ) {
                        System.out.println("Current Button: " + ((JToggleButton)e.getSource()).getText());
                        ((JToggleButton)e.getSource()).setSelected(false);
                        return;
                    }

                    if ( ((JToggleButton)e.getSource()).isSelected() ) {
                        String buttonText = ((JToggleButton)e.getSource()).getText();
                        addToQueue(buttonText);

                    } else if ( !((JToggleButton)e.getSource()).isSelected() ) {
                        ((JToggleButton)e.getSource()).setSelected(true);

                    }
                }
            });
            buttonPanel.add(button);
        }
        elevatorPanel.add(buttonPanel, BorderLayout.CENTER);
    
        // Create confirm button and add to panel
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Move elevator to toggled floor after 0.5 seconds
                for (Component component : buttonPanel.getComponents()) {
                    if (component instanceof JToggleButton && ((JToggleButton) component).isSelected()) {
                        //buttonQueueSort(floorLabel.getText().substring(7));
                        int qSize = buttonQueue.size();
                        for (int i=0; i<qSize; i++) {
                            try{
                                Thread.sleep(800);
                              }catch(InterruptedException ex){ 
                                System.out.println("Womp Womp");
                            }
                            JLabel floorLabel = (JLabel)elevatorPanel.getComponent(0);
                            System.out.println(buttonQueue);
                            String currentButtonValue = buttonQueue.peek();
                            floorLabel.setText("Floor: " + currentButtonValue);
                            floorLabel.paintImmediately(floorLabel.getVisibleRect());
                            JToggleButton currentButton = ((JToggleButton)buttonPanel.getComponent(dictionary.get(Integer.parseInt(currentButtonValue))));
                            currentButton.setSelected(false);
                            currentButton.paintImmediately(currentButton.getVisibleRect());
                            buttonQueue.remove();
                        }
                    }
                }
            }
        });
        elevatorPanel.add(confirmButton, BorderLayout.SOUTH);

        
        // Set elevator panel size and return
        elevatorPanel.setPreferredSize(new Dimension(180, 300));
        return elevatorPanel;
    }

    public void addToQueue(String value) {
        buttonQueue.add(value);
    }

    public void buttonQueueSort(String curFloor) {
        int[] tempQueue = new int[buttonQueue.size()];
        int[] endQueue = new int[buttonQueue.size()];
        Arrays.sort(tempQueue);
        int tempIndex = -1;
        for (int i : tempQueue) {
            int j=0;
            if (Integer.parseInt(curFloor) == i) {
                tempIndex = j;
                endQueue[j-tempIndex] = i;
            }
            j++;
        }

        for (int i=0; i<tempQueue.length-tempIndex; i++) {

        }

    }

    public static void main(String[] args) {
        ElevatorGUI2 ElevatorGUI2 = new ElevatorGUI2();
    }
}