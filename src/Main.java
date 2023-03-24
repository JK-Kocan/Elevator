import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Main {
    
    public static void main(String[] args) {
        /*
        JFrame frame = new JFrame(); //creates a frame
        frame.setTitle("Elevator GUI"); //sets frame title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits application when "x" button is pressed
        frame.setResizable(false); //prevent frame from being resized
        frame.setSize(1280,1024); //sets x and y dimensions of frame
        frame.setVisible(true); //make frame visible

        ImageIcon image = new ImageIcon("imgs/elevator_icon.png"); //creates an ImageIcon
        frame.setIconImage(image.getImage()); //change icon of frame
        frame.getContentPane().setBackground(new Color(85,85,85)); //change color of background
        */

        UserFrame testFrame = new UserFrame("Elevator GUIs");
    }  
}
