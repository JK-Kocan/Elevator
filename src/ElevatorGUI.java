import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class ElevatorGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel elevatorPanel;
    private Queue<String> buttonQueue = new LinkedList<>();
    Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();

    public ElevatorGUI() {
        setTitle("Elevator GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        // Create main panel and add elevator panels
        mainPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        elevatorPanel = createElevatorPanel("Elevator Buttons");
        mainPanel.add(elevatorPanel);
    
        add(mainPanel);
        setVisible(true);

    }
    
    // Creates a panel for an elevator with button panel and floor label
    private JPanel createElevatorPanel(String elevatorName) {
        JPanel elevatorPanel = new JPanel(new BorderLayout());
        elevatorPanel.setBorder(BorderFactory.createTitledBorder(elevatorName));
    
        /*
        // Create button panel and add buttons (15 buttons)
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
        */

        // Create button panel and add buttons (5 buttons)
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3, 5, 3));
        int[] buttonSet = new int[5];
        int j = 0;
        for (int i = 5; i > 0; i--) {
            buttonSet[j] = i;
            dictionary.put(i, j);
            j++;
        }

        // Create floor label and add to panel
        JLabel floorLabel = new JLabel("Floor: 1");
        floorLabel.setHorizontalAlignment(JLabel.CENTER);
        elevatorPanel.add(floorLabel, BorderLayout.NORTH);

        // Sets value for each button and what each button does when pressed
        for (int i = 0; i < buttonSet.length; i++) {
            String floor = Integer.toString(buttonSet[i]);
            JToggleButton button = new JToggleButton(floor);
            button.setPreferredSize(new Dimension(15, 10));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // if button matches current floor, doesnt toggle on
                    if ( ((JToggleButton)e.getSource()).getText().equals(floorLabel.getText().substring(7)) ) {
                        ((JToggleButton)e.getSource()).setSelected(false);
                        return;
                    }

                    // if button gets selected, adds to queue
                    if ( ((JToggleButton)e.getSource()).isSelected() ) {
                        String buttonText = ((JToggleButton)e.getSource()).getText();
                        addToQueue(buttonText);

                    // if button is already selected, keeps toggle on (locks it)
                    } else if ( !((JToggleButton)e.getSource()).isSelected() ) {
                        ((JToggleButton)e.getSource()).setSelected(true);

                    }
                }
            });
            buttonPanel.add(button);
        }
        elevatorPanel.add(buttonPanel, BorderLayout.CENTER);
        
        testQueueSort();

        // Create confirm button and add to panel
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Move elevator to toggled floor after 0.8 seconds
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
                            //System.out.println(buttonQueue);
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

    public void testQueueSort() {
        String curFloor = "9";
        int[] arr = { 8,3,1,10,2 };
        Queue<Integer> buttonQueue = new LinkedList<>();
        for (int i : arr) {
            buttonQueue.add(i);
        }
        int queueSize = buttonQueue.size();

        LinkedList <Integer> tempQueue = new LinkedList<Integer>();
        LinkedList <Integer> endQueue = new LinkedList<Integer>();

        for (int i=0; i<queueSize; i++) {
            tempQueue.add(buttonQueue.poll());
        }

        if (Integer.parseInt(curFloor) < tempQueue.get(0)) { // will go up then down
            Collections.sort(tempQueue);
            for (int j=0; j<tempQueue.size(); j++) {
                int i=tempQueue.get(j);
                if (Integer.parseInt(curFloor) < i) {
                    endQueue.add(i);
                }
            }

            for (int j=0; j<tempQueue.size(); j++) {
                int i=tempQueue.get(j);
                if (Integer.parseInt(curFloor) > i) {
                    endQueue.add(i);
                }
            }

        } else if (Integer.parseInt(curFloor) > tempQueue.get(0)) { // will go down then up
            Collections.sort(tempQueue);
            for (int j=0; j<tempQueue.size(); j++) {
                int i=tempQueue.get(j);
                if (Integer.parseInt(curFloor) > i) {
                    endQueue.add(i);
                }
            }

            for (int j=0; j<tempQueue.size(); j++) {
                int i=tempQueue.get(j);
                if (Integer.parseInt(curFloor) < i) {
                    endQueue.add(i);
                }
            }
            
        }

        System.out.println(endQueue);
    }

    public void buttonQueueSort(String curFloor) {
        int[] tempQueue = new int[buttonQueue.size()];
        int[] endQueue = new int[buttonQueue.size()];
        if (Integer.parseInt(curFloor) > tempQueue[0]) { // will go up then down
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
        } else if (Integer.parseInt(curFloor) < tempQueue[0]) { // will go down then up

        }
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
        ElevatorGUI ElevatorGUI = new ElevatorGUI();
    }
}