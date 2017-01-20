/*
 * Classname
 *
 * Version information
 *
 * Date
 *
 */

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.InterruptedIOException;

public class Assn1_15rws {

    //define the score needed to win the game
    private static final int WINNING_SCORE = 100;
    //define the size of dice you want to play the game with
    //NOTE: Only die with six or less sides will work for current implementation
    private static final int DICE_SIDES = 6;
    //define the number of rolls that a user could take per turn
    private static final int NUMBER_ROLLS_PER_TURN = 2;

    //define the different type of turns
    private enum Turn {
        player,
        computer
    }

    //make scanner class attribute, passing in the system input stream to scanner
    private static Scanner scanner = new Scanner(System.in);
    //create flag to keep track of whose turn it is, by default player gets first turn
    private static Turn turn = Turn.player;
    //define a counter for rounds in the game, default to 1 as the game starts on round 1
    private static int round = 1;
    //define counter for players game sum
    private static int playerSum = 0;
    //define counter for computers game sum
    private static int computerSum = 0;

    /**
     * Main method of the program.
     * @param args
     */
    public static void main(String[] args) {

    }


    //Printing Methods

    public static void printRoll(int ... rolls) {
        switch (turn) {
            case player:
                System.out.print("Player rolled ");
                break;
            case computer:
                System.out.print("Computer rolled ");
                break;
        }
        String separator = "";
        for (int roll : rolls) {
            System.out.print(separator);
            System.out.printf("%s", numToString(roll));
            separator = " + ";
        }
    }

    //prints turn sum
    public static void printTurnSum(int turnSum) {
        switch (turn) {
            case player:
                System.out.printf("Player's turn sum is: %d and game sum is: %d.",turnSum,playerSum);
                break;
            case computer:
                System.out.printf("Computers's turn sum is: %d and game sum is: %d.",turnSum,computerSum);
                break;
        }
    }

    //prints turn summary
    public static void printTurnSummary() {

    }

    //prints when round over
    public static void printRoundOver() {

    }

    //Checks

    //prompts player to see if wants to roll
    public static boolean rollAgain() {
        return true;
    }

    public static boolean gameComplete() {
        //check if either the computer score or user score is above the score needed to win the game
        if (playerSum > WINNING_SCORE || computerSum > WINNING_SCORE) {
            //if either score is over the winning score return true
            return true;
        }
        //otherwise keep playing the game, no one has won
        return false;
    }

    //Utility Methods

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
            //return error string if num is outside 0 to 6 inclusive bounds
            default:
                return "Number outside of the bounds for current game implementation.";
        }
    }

    //Dice Rolling Methods

    /**
     * Rolls a virtual dice and returns the string equivalent.
     * @return A random number between 0 and DICE_SIDES in string format
     * NOTE: DICE_SIDES constant is used as bound for random generator.
     */
    public static int rollDice() {
        //seed the random generator with the current time in milliseconds
        Random generator = new Random(System.currentTimeMillis());
        //the string of the random number
        return generator.nextInt(DICE_SIDES+1);
    }
}
