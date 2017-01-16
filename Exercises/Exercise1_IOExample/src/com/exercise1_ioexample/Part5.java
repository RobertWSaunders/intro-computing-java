//define package
package com.exercise1_ioexample;
//import io exception
import java.io.IOException;

//define class
public class Part5 {
    //define main method
    public static void main(String[] args) {
        //create array of type byte, holds ten bytes
        byte[] buffer = new byte[10];
        //declare integer to count how many characters were entered
        int numRead = -1;
        //ask the user to type something
        System.out.print("Please enter something: ");
        //try catch block to handle exception
        try {
            //returns number of bytes in buffer
            numRead = System.in.read(buffer);
        } catch (IOException e) {
            //output to user if an exception is caught
            System.out.println("Should not have got here!");
        }
        //display how many characters user entered
        System.out.println("You typed in " + numRead + " characters.");
        //display the bytes the user entered
        System.out.println("As bytes:");
        for (int i = 0; i < numRead; i++)
            System.out.println(buffer[i]);
        //display the characters the user entered
        System.out.println("As characters:");
        for (int i = 0; i < numRead; i++)
            //map the bytes to char
            System.out.println((char)buffer[i]);
    }
}