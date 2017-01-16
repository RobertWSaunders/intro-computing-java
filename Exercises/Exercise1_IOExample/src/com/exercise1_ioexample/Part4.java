//define the package
package com.exercise1_ioexample;
//import the scanner class for console input
import java.util.Scanner;
//import the exception for incorrect user input
import java.util.InputMismatchException;

//define class
public class Part4 {
    //define main method
    public static void main(String[] args) {
        //user number, defaults to zero
        double userNum = 0;
        //initialize a new scanner for user input
        Scanner scanner = new Scanner(System.in);
        //define a boolean to check if the user input is good
        boolean inputOK = false;
        //define dump string to clear keyboard buffer, scanner caveat
        String dump;
        //loop through user prompts until user inputs a legal double
        while (!inputOK) {
            //ask the user for their number
            System.out.print("Enter a number: ");
            try {
                //retrieve the users number and convert to double
                userNum = scanner.nextDouble();
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //set the boolean to true
                inputOK = true;
                //catch the exception if the users number isn't a double
            } catch (InputMismatchException e) {
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //notify user that exception occurred
                System.out.println("\"" + dump + "\" is not a legal double, " +
                        "please try again!");
            }
        }
        //close the scanner
        scanner.close();
        //let the user know their number
        System.out.printf("\nYour number is: %f", userNum);
    }
}
