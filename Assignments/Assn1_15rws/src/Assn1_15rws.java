/*
 * Classname
 *
 * Version information
 *
 * Date
 *
 * Copyright notice
 */

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Assn1_15rws {

    //define the score needed to win the game
    private static final int WINNING_SCORE = 100;

    //define the different type of turns
    private enum Turn {
        player,
        computer
    }

    //make scanner class attribute, passing in the system input stream to scanner
    private static Scanner scanner = new Scanner(System.in);
    //create flag to keep track of whose turn it is, by default player gets first turn
    private static Turn turn = Turn.player;

    /**
     * Main method of the program.
     * @param args
     */
    public static void main(String[] args) {
       if (turn == Turn.player)
            System.out.print("Player's Turn");
       else
           System.out.print("Computer's Turn");
    }


    /**
     *
     * @param userScore
     * @param computerScore
     * @return
     */
    public static boolean gameComplete(int userScore, int computerScore) {
        //check if either the computer score or user score is above the score needed to win the game
        if (userScore > WINNING_SCORE || computerScore > WINNING_SCORE) {
            //if either score is over the winning score return true
            return true;
        }
        //otherwise keep playing the game, no one has won
        return false;
    }

    /**
     * Converts an int to its english word (string) equivalent, compatible for 0 to 6 inclusive.
     * @param num Integer that gets converted to its word equivalent.
     * @return The english word (string) equivalent to passed num, default to "" if num outside bounds.
     */
    public static String numToString(int num) {
        //switch statement for passed num
        switch(num) {
            case 0:
                return "zero";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            //return default string if num is outside 0 to 6 inclusive bounds
            default:
                return "";
        }
    }

    public static String rollDice(int times) {
        Random generator = new Random(System.currentTimeMillis());
        return "";
    }
}
