//define package
package com.exercise1_ioexample;
//import the pane for GUI
import javax.swing.JOptionPane;

//define class
public class Part6 {
    //define main method
    public static void main(String[] args) {
        //define string to get user input and show dialogue box
        String userInput = JOptionPane.showInputDialog("Enter a number");
        //get the integer from the users input
        int userNum = Integer.parseInt(userInput);
        //show dialogue message to the user
        JOptionPane.showMessageDialog(null, "Your number is: " + userNum);
    }
}