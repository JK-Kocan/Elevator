import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class test {
    
    public static void main() {
        String curFloor = "7";
        int[] arr = { 8,3,1,10,2,9 };
        Queue<Integer> buttonQueue = new LinkedList<>();
        for (int i : arr) {
            buttonQueue.add(i);
        }

        LinkedList <Integer> tempQueue = new LinkedList<Integer>();
        LinkedList <Integer> endQueue = new LinkedList<Integer>();
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

}
