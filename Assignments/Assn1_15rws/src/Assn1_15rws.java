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
        player,
        computer
    }

    //define the different type of turns
    private enum DiceCombination {
        snakeeyes,
        singleone,
        matching,
        random
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
            initiateTurn();
        }
        //print the winner
        printWinner();
    }

    public static void initiateTurn() {
        //print whose tur it is
        printWhoseTurn();
        int rollSum = 0;
        do {
            int[] rolls = collectRolls();
            //if the rolls are good then give the user the option to roll again
            printRoll(rolls);
            switch (validateRolls(rolls, rollSum)) {
                case random:
                    rollSum += IntStream.of(rolls).sum();
                    addToGameSum(rollSum);
                    printTurnSum(rollSum);
                    break;
                case snakeeyes:
                    printTurnSummary();
                    switchTurns();
                    break;
                case matching:
                    //skip roll again, automatically gets rolled again
                    break;
                case singleone:
                    //turn over and turn sum set to zero

                    break;
            }
        } while (rollAgain());
    }

    /* PRINTING METHODS */

    public static void printWinner() {
        if (computerSum > playerSum) {
            System.out.print("Computer Wins!");
        }
        else {
            System.out.print("Player Wins!");
        }
    }

    public static void printRoll(int ... rolls) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (turn) {
            case player:
                stringBuilder.append("Player rolled ");
                break;
            case computer:
                stringBuilder.append("Computer rolled ");
                break;
        }
        String seperator = "";
        for (int roll : rolls) {
            stringBuilder.append(seperator);
            stringBuilder.append(String.format("%s", numToString(roll)));
            seperator = " + ";
        }
        stringBuilder.append("\n");
        System.out.print(stringBuilder.toString());
    }

    public static void printWhoseTurn() {
        switch (turn) {
            case player:
                System.out.printf("Player's turn:\n",turn);
                break;
            case computer:
                System.out.printf("Computer's turn:\n",turn);
                break;
        }
    }

    //prints turn sum
    public static void printTurnSum(int turnSum) {
        switch (turn) {
            case player:
                System.out.printf("Player's turn sum is: %d and game sum is: %d.\n",turnSum,playerSum);
                break;
            case computer:
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


    //prompts player to see if wants to roll
    public static boolean rollAgain() {
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

        if ((char) buffer[0] == 'y') {
            return true;
        }
        else if ((char) buffer[0] == 'n') {
            turn = Turn.computer;
            return false;
        }
        else {
            return false;
        }
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

    /* UTILITY METHODS */

    //where validation of turn happens
    public static DiceCombination validateRolls(int[] rolls, int rollSum) {
        //checks first case, are all rolls the same
        for(int i=1; i<rolls.length; i++){
            if((rolls[0] == rolls[i]) && (rolls[0] == 1)){
                resetGameSum();
                return DiceCombination.snakeeyes;
            }
        }
        //checks second case, if any rolls equal one
        for(int roll : rolls){
            if(roll == 1){
                rollSum = 0;
                return DiceCombination.singleone;
            }
        }

        //checks third case, if rolls all match
        for(int i=1; i<rolls.length; i++){
            if(rolls[0] == rolls[i]){
                resetGameSum();
                return DiceCombination.matching;;
            }
        }
        return DiceCombination.random;
    }

    public static void switchTurns() {
        switch (turn) {
            case player:
                turn = Turn.computer;
                break;
            case computer:
                turn = Turn.player;
                break;
        }
    }


    public static void addToGameSum(int amountAdded) {
        switch (turn) {
            case player:
                playerSum += amountAdded;
                break;
            case computer:
                computerSum += amountAdded;
                break;
        }
    }

    public static void resetGameSum() {
        switch (turn) {
            case player:
                System.out.print("Setting Player's game sum to zero!\n");
                playerSum = 0;
                break;
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

    /* DICE ROLLING METHODS */

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

    /**
     * Will collect n (represented by NUMBER_ROLLS_PER_TURN) rolls and return an array full of the
     * integers that represent a roll.
     * @return An array with n (represented by NUMBER_ROLLS_PER_TURN) integers that represent a dice roll
     */
    public static int[] collectRolls() {
        int[] rolls = new int[NUMBER_ROLLS_PER_TURN];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rollDice();
        }
        return rolls;
    }
}
