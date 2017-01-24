/*
 * Assn1_15rws
 * Purpose: The purpose of this assignment is to practice designing methods, using loops, conditionals and this
 * console I/O. This program allows a user to play against a computer in the two dice variation of the Game of Pig.
 * Version 1.0
 * January 24th, 2017
 * Author: Robert Saunders (10194030)
 */

import b.b.P;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.stream.*;
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
        player, //player
        computer //computer
    }

    //define the different dice combinations either the computer or player could role
    private enum DiceCombination {
        snakeeyes, //snakeeyes, when all dice are 1's
        singleone, //singleone, one dice is 1
        matching, //matching, when all dice are matching
        random //random, when dice are all different
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
        // play the game as log as no has won the game
        while (!gameComplete()) {
            beginTurn();
        }
        //print the winner
        printWinner();
    }

    public static void beginTurn() {
        //print whose tur it is
        printWhoseTurn();
        //hold the current turn sum
        int turnSum = 0;
        //define array that will hold NUMBER_ROLLS_PER_TURN rolls as ints
        int[] rolls;
        do {
            //fill the rolls array with NUMBER_ROLLS_PER_TURN of rolls
            rolls = collectRolls();
            //if the rolls are good then give the user the option to roll again
            printRoll(rolls);
        } while (rollAgain(validateRolls(rolls),rolls,turnSum));
        //end the turn
        endTurn();
    }

    public static void endTurn() {
        //print the turn summary
        printTurnSummary();
        //because players always go first we know that the end of the round is when it's the computers turn
        if (turn == Turn.computer) {
            //end the round
            endRound();
        }
        else {
            //otherwise switch turns
            switchTurns();
        }
    }

    public static void beginRound() {
        beginTurn();
    }

    public static void endRound() {
        printRoundOver();
        beginRound();
    }

    public static void performRuleLogic(int[] rolls, int turnSum, DiceCombination combination) {
        //get the dice combination
        switch (combination) {
            //if the dice are all random, proceed with game noramlly
            case random:
                //add sum of rolls to the turn sum
                turnSum += IntStream.of(rolls).sum();
                //add the turn sum to the game sum
                addToGameSum(turnSum);
                //print the turn sum
                printTurnSum(turnSum);
                break;
            //if all dice are 1's
            case snakeeyes:
                //reset the game sum for whoever got snake eys
                resetGameSum();
                break;
            case singleone:
                turnSum = 0;
                break;
        }
    }




    /* PRINTING METHODS */

    /**
     * Prints the winner of the game.
     */
    public static void printWinner() {
        if (computerSum > playerSum) {
            System.out.print("Computer Wins!");
        }
        else {
            System.out.print("Player Wins!");
        }
    }

    /**
     * Prints a roll in the format "Player rolled two + five"
     * @param rolls Rolls to be printed, can be n number of rolls.
     */
    public static void printRoll(int ... rolls) {
        //define a string builder
        StringBuilder stringBuilder = new StringBuilder();
        //append corresponding string depending on whose turn it is
        switch (turn) {
            case player:
                stringBuilder.append("Player rolled ");
                break;
            case computer:
                stringBuilder.append("Computer rolled ");
                break;
        }
        //define a separator outside of for-each loop
        String separator = "";
        //iterate through each roll passed into method
        for (int roll : rolls) {
            //append separator, on first iteration it is blank
            stringBuilder.append(separator);
            //append the roll in its english word equivalent
            stringBuilder.append(String.format("%s", numToString(roll)));
            //set the separator for any iteration after the first
            separator = " + ";
        }
        //append a new line character to the string for styling
        stringBuilder.append("\n");
        //print the string to the console
        System.out.print(stringBuilder.toString());
    }

    /**
     * Prints whoever's turn it is in the format "Player's turn:"
     */
    public static void printWhoseTurn() {
        //determine whose turn it is
        switch (turn) {
            case player:
                //print players turn
                System.out.printf("Player's turn:\n",turn);
                break;
            case computer:
                //print computers turn
                System.out.printf("Computer's turn:\n",turn);
                break;
        }
    }

    /**
     * Prints the turn summary in the format "Player's turn sum is: 6 and game sum is: 6"
     * @param turnSum The current turns sum to be printed.
     */
    public static void printTurnSum(int turnSum) {
        //determine whose turn it is
        switch (turn) {
            case player:
                //print players version
                System.out.printf("Player's turn sum is: %d and game sum is: %d.\n",turnSum,playerSum);
                break;
            case computer:
                //print computer version
                System.out.printf("Computers's turn sum is: %d and game sum is: %d.\n",turnSum,computerSum);
                break;
        }
    }

    //prints turn summary
    public static void printTurnSummary() {

    }

    //prints when round over
    public static void printRoundOver() {

    }


    /**
     * Asks the user if they want to roll again, called when the user is able to roll again using game rules.
     * @return True if the user does want to roll again, false otherwise.
     */
    public static boolean rollAgain(DiceCombination combination, int[] rolls, int turnSum) {

        performRuleLogic(rolls,turnSum, combination);

        if (combination == DiceCombination.random) {
            //create array of type byte, holds one bytes, only looking for one response
            byte[] buffer = new byte[1];
            //declare integer to count how many characters were entered
            int numRead = -1;
            //ask the user to type something
            System.out.print("Roll again? (y/n)");
            //try catch block to handle exception
            try {
                //returns number of bytes in buffer
                numRead = System.in.read(buffer);
            } catch (IOException e) {
                //output to user if an exception is caught
                System.out.println("Should not have got here!");
            }

            byte b = buffer[0];
            char c = (char) b;
            String test = String.valueOf(c);

            if (test == "y") {
                return true;
            } else if ((char)buffer[0] == 'n') {
                return false;
            }
            else {
                return false;
            }
        }
        else if (combination == DiceCombination.matching)  {
            return true;
        }
        else  {
            return false;
        }
    }

    /**
     * Checks if the game is complete based off of the WINNING_SCORE.
     * @return True if the game has been won, false otherwise.
     */
    public static boolean gameComplete() {
        //check if either the computer score or user score is above the score needed to win the game
        if (playerSum > WINNING_SCORE || computerSum > WINNING_SCORE) {
            //if either score is over the winning score return true
            return true;
        }
        //otherwise keep playing the game, no one has won
        return false;
    }

    /**
     * Returns the combination that the dice that were roll are in.
     * @param rolls An array of full of ints that represent the rolls.
     * @return The correct DiceCombination enum for the given dice combination.
     */
    public static DiceCombination validateRolls(int[] rolls) {
        //must use a for loop because can be n number of rolls
        //checks first case, are all rolls the same
        for(int i=1; i<rolls.length; i++){
            //check if all rolls are the same and equal to 1
            if((rolls[0] == rolls[i]) && (rolls[0] == 1)){
                //return the snake eyes combination
                return DiceCombination.snakeeyes;
            }
        }
        //checks second case, if any rolls equal one
        for(int roll : rolls){
            //check if roll equals 1
            if(roll == 1) {
                //return the singleone combination
                return DiceCombination.singleone;
            }
        }

        //checks third case, if rolls all match
        for(int i=1; i<rolls.length; i++){
            //check if all rolls match
            if(rolls[0] == rolls[i]){
                //return the matching combination
                return DiceCombination.matching;
            }
        }
        //otherwise retunr the random combination
        return DiceCombination.random;
    }


    /**
     * Sets the 'turn' class variable to the inverse of what it currently is, essentially switching turns.
     */
    public static void switchTurns() {
        //determine whose turn it currently is
        switch (turn) {
            case player:
                //set the turn to be the computers
                turn = Turn.computer;
                break;
            case computer:
                //set the turn to be the players
                turn = Turn.player;
                break;
        }
    }

    /**
     * Adds a given amount to either the player game sum or computer game sum based off of whose turn it is.
     * @param amountAdded The amount being added to either the player sum or computer sum.
     */
    public static void addToGameSum(int amountAdded) {
        //determine whose turn it is
        switch (turn) {
            case player:
                //add given amount to player sum
                playerSum += amountAdded;
                break;
            case computer:
                //add given amount to computer sum
                computerSum += amountAdded;
                break;
        }
    }

    /**
     * Resets either the player game sum or the game sum to zero based off of whose turn it is.
     * Also prints a message to the console to let the player know a game sum is being set to zero.
     */
    public static void resetGameSum() {
        //determine whose turn it is
        switch (turn) {
            //reset the player sum to zero and print to console
            case player:
                System.out.print("Setting Player's game sum to zero!\n");
                playerSum = 0;
                break;
            //reset the computer sum to zero and print to console
            case computer:
                System.out.print("Setting Computer's game sum to zero!\n");
                computerSum = 0;
                break;
        }
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
            //return error string if num is outside 0 to 6 inclusive bounds
            default:
                return "Number outside of the bounds for current game implementation.";
        }
    }

    /**
     * Rolls a virtual dice and returns the dice number as an int.
     * @return A random number between 0 and DICE_SIDES as an int.
     * NOTE: DICE_SIDES constant is used as bound for random generator.
     */
    public static int rollDice() {
        //seed the random generator with the current time in milliseconds
        Random generator = new Random(System.currentTimeMillis());
        //the string of the random number
        return generator.nextInt(DICE_SIDES+1);
    }

    /**
     * Will collect n (represented by NUMBER_ROLLS_PER_TURN) rolls and return an array full of ints.
     * @return An array with n (represented by NUMBER_ROLLS_PER_TURN) ints that represent the dice rolls.
     */
    public static int[] collectRolls() {
        //create array of size NUMBER_ROLLS_PER_TURN
        int[] rolls = new int[NUMBER_ROLLS_PER_TURN];
        //add rolls into the array
        for (int i = 0; i < rolls.length; i++)
            rolls[i] = rollDice();
        //return the rolls array
        return rolls;
    }
}
