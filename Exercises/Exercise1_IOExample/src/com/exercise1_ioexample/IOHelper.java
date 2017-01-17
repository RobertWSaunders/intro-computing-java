package com.exercise1_ioexample;
//import the scanner class for console input
import java.util.Scanner;
//import the exception for incorrect user input
import java.util.InputMismatchException;

//define class
public class IOHelper {

    //define scanner class attribute, can use in all methods
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(int low, String prompt, int high) {
        int userInt = -1;
        //define a boolean to check if the user input is good
        boolean inputValidation = false;
        //define dump string to clear keyboard buffer, scanner caveat
        String dump;
        //loop through user prompts until user inputs a legal double
        while (!inputValidation) {
            //ask the user for their number
            System.out.print(prompt);
            try {
                //retrieve the users number and convert to double
                userInt = scanner.nextInt();
                //check if the user input is between the limits
                if (!(userInt > low && userInt < high)) {
                    System.out.println("The number is outside of the legal limits!");
                    continue;
                }
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //set the boolean to true
                inputValidation = true;
                //catch the exception if the users number isn't a double
            } catch (InputMismatchException e) {
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //notify user that exception occurred
                System.out.println("\"" + dump + "\" is not an integer!");
            }
        }
        //return what the user selected
        return userInt;
    }

    public static int getInt(int low, String prompt) {
        return getInt(low,prompt,Integer.MAX_VALUE);
    }
    public static int getInt(String prompt, int high) {
        return getInt(Integer.MIN_VALUE,prompt,high);
    }

    public static int getInt(String prompt) {
        return getInt(Integer.MIN_VALUE,prompt,Integer.MAX_VALUE);
    }

    public static int getInt() {
        return getInt(Integer.MIN_VALUE,"Please enter any number:",Integer.MAX_VALUE);
    }

    public static double getDouble(double low, String prompt, double high) {
        double userDouble = -1;
        //define a boolean to check if the user input is good
        boolean inputValidation = false;
        //define dump string to clear keyboard buffer, scanner caveat
        String dump;
        //loop through user prompts until user inputs a legal double
        while (!inputValidation) {
            //ask the user for their number
            System.out.print(prompt);
            try {
                //retrieve the users number and convert to double
                userDouble = scanner.nextDouble();
                //check if the user input is between the limits
                if (!(userDouble > low && userDouble < high)) {
                    System.out.println("The number is outside of the legal limits!");
                    continue;
                }
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //set the boolean to true
                inputValidation = true;
                //catch the exception if the users number isn't a double
            } catch (InputMismatchException e) {
                //clear the keyboard buffer
                dump = scanner.nextLine();
                //notify user that exception occurred
                System.out.println("\"" + dump + "\" is not a double!");
            }
        }
        //return what the user selected
        return userDouble;
    }

    public static double getDouble(double low, String prompt) {
        return getDouble(low,prompt,Double.MAX_VALUE);
    }
    public static double getDouble(String prompt, double high) {
        return getDouble(Double.MIN_VALUE,prompt,high);
    }
    public static double getDouble(String prompt) {
        return getDouble(Double.MIN_VALUE,prompt,Double.MAX_VALUE);
    }
    public static double getDouble() {
        return getDouble(Double.MIN_VALUE,"Please enter any double:",Double.MAX_VALUE);
    }
}
