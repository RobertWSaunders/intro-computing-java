//define package
package com.exercise1_ioexample;

//import message dialogue classes
import javax.swing.*;

//define class
public class Part3 {
    //define main method
    public static void main(String[] args) {
        //define test value
        int testVal = 20;
        //show the test value in a message dialog
        //JOptionPane.showMessageDialog(null, "The number is " + testVal);
        JOptionPane.showInputDialog(null,"y","x",JOptionPane.QUESTION_MESSAGE);
    }
}
