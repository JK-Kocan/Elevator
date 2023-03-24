import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class UserFrame extends JFrame {
    
    UserFrame(String frameTitle) {
        this.setTitle(frameTitle); //sets frame title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits application when "x" button is pressed
        this.setResizable(false); //prevent frame from being resized
        this.setSize(1280,1024); //sets x and y dimensions of frame
        this.setVisible(true); //make frame visible

        ImageIcon image = new ImageIcon("imgs/elevator_icon.png"); //creates an ImageIcon
        this.setIconImage(image.getImage()); //change icon of frame
        this.getContentPane().setBackground(new Color(85,85,85)); //change color of background
    }
}
