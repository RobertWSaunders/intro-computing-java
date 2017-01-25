/**
 * The purpose of this assignment is to practice designing methods, using loops, conditionals and console I/O.
 * This program allows a user to play against a computer in the two dice variation of the Game of Pig. This
 * implementation is configurable to play the n dice variation (default 2), a larger winning score (default
 * 100) and even the number of sides on the dice.
 *
 * @author Robert Saunders (10194030)
 * @version 1.0.0
 */

import b.b.b.a.U;

import java.util.Random;
import java.io.IOException;
import java.util.stream.*;

public class Assn1_15rws {

    //define the score needed to win the game
    private static final int WINNING_SCORE = 100;
    //define the size of dice you want to play the game with
    //NOTE: Only die with six or less sides will work for current implementation
    private static final int DICE_SIDES = 6;
    //define the number of rolls that a user could take per turn
    private static final int NUMBER_ROLLS_PER_TURN = 2;
    //define the amount of turn sum the computer can accumulate before ending a turn
    private static final int COMPUTER_TURN_SUM_BOUND = 40;

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

    //define the different dice combinations either the computer or player could role
    private enum UserInputType {
        rollAgain, //snakeeyes, when all dice are 1's
        startRound, //singleone, one dice is 1
    }

    //create flag to keep track of whose turn it is, by default player gets first turn
    private static Turn turn = Turn.player;
    //define a counter for rounds in the game
    private static int round = 1;
    //define counter for turn sum, gets reset every turn
    private static int turnSum;
    //define counter for players game sum
    private static int playerSum = 0;
    //define counter for computers game sum
    private static int computerSum = 0;

    ///////////////////////////
    /* MAIN EXECUTION METHOD */
    ///////////////////////////

    /**
     * Main method of the program.
     * @param args
     */
    public static void main(String[] args) {
        // play the game as long as no has won the game
        while (!gameComplete()) {
            //if no one has won then begin turn
            beginRound();
        }
        //if someone has won then print the winner
        printWinner();
    }

    /////////////////////////
    /* GAME LOGIC METHODS */
    ///////////////////////

    /**
     * Begins a round.
     */
    public static void beginRound() {
        if (turn == Turn.player) {
            //increment the round number
            round++;
        }
        //begin a turn in the round
        beginTurn();
    }

    /**
     * Ends a round.
     */
    public static void endRound() {
        if (startNewRound()) {
            switchTurns();
            beginRound();
        }
    }

    /**
     * Begins a turn.
     */
    public static void beginTurn() {
        //print whose turn it is
        printWhoseTurn();
        //gets reset every turn
        turnSum = 0;
        //define array that will hold NUMBER_ROLLS_PER_TURN rolls as ints
        int[] rolls;
        //Perform the turn
        do {
            //fill the rolls array with NUMBER_ROLLS_PER_TURN of rolls
            rolls = collectRolls(NUMBER_ROLLS_PER_TURN);
            //print what was rolled
            printRoll(rolls);
        } while (rollAgain(rolls)); //roll again logic, computes rules and prompts user to roll again if they can
        //end the turn
        endTurn();
    }

    /**
     * Ends a turn.
     */
    public static void endTurn() {
        //if the game is not over, end the turn, otherwise end the game
        if (!gameComplete()) {
            //because players always go first we know that the end of the round is when it's the computers turn
            if (turn == Turn.computer) {
                //end the round
                endRound();
            } else {
                //print the turn summary
                printTurnSummary();
                //otherwise switch turns
                switchTurns();
            }
        }
    }

    /**
     * Determines if the
     * @return True if the user does want to roll again, false otherwise.
     */
    public static boolean rollAgain(int[] rolls) {
        //get the combination the dice are in
        DiceCombination combination = getDiceCombination(rolls);
        //check if the given dice is allowed to roll again.
        if (isAllowedToRollAgain(rolls, combination)) {
            //check if this roll has made either the computer or player win
            //needs to be nested in this statement because rule logic needs to be applied
            if (!gameComplete())
                //if the combination is not matching and its the player's turn
                if ((combination != DiceCombination.matching) && (turn == Turn.player))
                    //ask the user if they want to roll again
                    return getUserInput("Roll again? (y/n)",UserInputType.rollAgain);
                    //in the event that the dice are matching, just don't ask user if they want to roll again
                    //also always allow the computer to roll, computer logic
                else {
                    if (combination != DiceCombination.matching) {
                        printMatchingMustRoll();
                    }
                    return true;
                }
            else {
                //someone has won the game, do not allow another roll
                return false;
            }
        }
        //by default return false if game rules do not permit them to roll again
        return false;
    }

    /**
     * Determines if the player or computer is allowed to roll by comparing what they rolled against the game rules.
     * @param rolls An array full of ints that represent the rolls.
     * @param combination The combination the dice are in.
     * @return True if the user is allowed to roll again, false otherwise.
     */
    public static boolean isAllowedToRollAgain(int[] rolls, DiceCombination combination) {
        //set the roll sum by calculating it
        int rollSum = calculateRollSum(rolls);
        //computer logic, if the computer exceeds COMPUTER_TURN_SUM_BOUND points in the turn, then just end the turn
        if (turn == Turn.computer && turnSum >= COMPUTER_TURN_SUM_BOUND) {
            //by default return false
            return false;
        }
        //shared logic for both computer and player
        else {
            //switch case through each die combinations
            switch (combination) {
                //if all dice are ones
                case snakeeyes:
                    //set the turn sum to zero
                    turnSum = 0;
                    //reset the game sum for whoever got snake eyes
                    resetGameSum();
                    //if snakeeyes cannot roll again
                    return false;
                case singleone:
                    //set the turn sum to zero
                    resetTurnSum();
                    //if there is a single one cannot roll again
                    return false;
                default:
                    //add the roll sum to the turn sum
                    turnSum += rollSum;
                    //add the turn sum to the game sum
                    addToGameSum(rollSum);
                    //print the turn sum
                    printTurnSum();
                    //for both matching and random combinations allow roll again
                    return true;
            }
        }
    }

    /**
     * Returns the combination that the dice are in.
     * @param rolls An array full of ints that represent the rolls.
     * @return The correct DiceCombination enum for the given dice combination.
     */
    public static DiceCombination getDiceCombination(int[] rolls) {
        //must use a for loop because can be n number of rolls
        //checks first case, are all rolls the same
        for(int i=1; i<rolls.length; i++){
            //check if all rolls are the same and equal to 1
            if((rolls[0] == rolls[i]) && (rolls[0] == 1))
                //return the snake eyes combination
                return DiceCombination.snakeeyes;
        }
        //checks second case, if any rolls equal one
        for(int roll : rolls){
            //check if roll equals 1
            if(roll == 1)
                //return the singleone combination
                return DiceCombination.singleone;
        }
        //checks third case, if rolls all match
        for(int i=1; i<rolls.length; i++){
            //check if all rolls match
            if(rolls[0] == rolls[i])
                //return the matching combination
                return DiceCombination.matching;
        }
        //otherwise return the random combination
        return DiceCombination.random;
    }


    /**
     * Prompts user to see if they want to roll again.
     * @return True if the user does want to roll again, false otherwise.
     */
    public static boolean getUserInput(String message, UserInputType type) {
        //never ask the computer if its wants to roll again, defaults to true
        //only ask the user if they want to roll again if the dice are random
        boolean inputOk = false;
        //create array of type byte, holds one bytes, only looking for one response
        byte[] buffer = new byte[1];
        //ask the user if they want to roll again
        System.out.printf("%s ",message);
        while (!inputOk) {
            //try catch block to handle exception
            try {
                //returns number of bytes in buffer
                System.in.read(buffer);
            } catch (IOException e) {
                //output to user if an exception is caught
                System.out.println("Should not have got here!");
            }
            if (type == UserInputType.rollAgain) {
                if ((char) buffer[0] == 'y') {
                    //if yes then return true
                    return true;
                } else if ((char) buffer[0] == 'n') {
                    //if no then return false
                    return false;
                }
            }
            if (type == UserInputType.startRound) {
                if ((char) buffer[0] == ' ') {
                    //if yes then return true
                    return true;
                }
            }
        }
        return false;
    }

    //////////////////////////////
    /* CONSOLE PRINTING METHODS */
    //////////////////////////////

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
        System.out.print("\n\nGAME OVER!");
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
                System.out.printf("\nPlayer's turn:\n",turn);
                break;
            case computer:
                //print computers turn
                System.out.printf("\nComputer's turn:\n",turn);
                break;
        }
    }

    public static void printMatchingMustRoll() {
        //determine whose turn it is
        switch (turn) {
            case player:

                System.out.print("Player must roll again!\n");
                break;
            case computer:
                System.out.print("Player must roll again!\n");
                break;
        }
    }


    /**
     * Prints the turn summary in the format "Player's turn sum is: 6 and game sum is: 6"
     */
    public static void printTurnSum() {
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
        System.out.printf("\nPlayer's sum is: %d, Computer's sum is: %d.\n",playerSum,computerSum);
    }

    //prints when round over
    public static boolean startNewRound() {
        return getUserInput(String.format("\nPlayer's sum is: %d, Computer's sum is: %d. Press <space><enter> to start round %d.",playerSum,computerSum, round), UserInputType.startRound);
    }

    ///////////////////////////
    /* GAME UTILITY METHODS */
    /////////////////////////

    /**
     * Checks if the game is complete based off of the WINNING_SCORE.
     * @return True if the game has been won, false otherwise, does not tell us who won.
     */
    public static boolean gameComplete() {
        //check if either the computer score or user score is above the score needed to win the game
        if (playerSum >= WINNING_SCORE || computerSum >= WINNING_SCORE) {
            //if either score is over the winning score return true
            return true;
        }
        //otherwise keep playing the game, no one has won
        return false;
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
     * Subtracts a given amount to either the player game sum or computer game sum based off of whose turn it is.
     * @param amountSubtracted The amount being subtracted to either the player sum or computer sum.
     */
    public static void subtractFromGameSum(int amountSubtracted) {
        //determine whose turn it is
        switch (turn) {
            case player:
                //add given amount to player sum
                playerSum -= amountSubtracted;
                break;
            case computer:
                //add given amount to computer sum
                computerSum -= amountSubtracted;
                break;
        }
    }

    /**
     * Calculates the sum of the rolls inside a given array of rolls.
     * @param rolls Array of ints that represent a die roll.
     * @return The sum of rolls.
     */
    public static int calculateRollSum(int[] rolls) {
        //add sum of rolls to get the roll sum
        return IntStream.of(rolls).sum();
    }

    /**
     * Resets either the player game sum or the game sum to zero based off of whose turn it is.
     * Also prints a message to the console to let the player know a game sum is being set to zero and their turn will be over.
     */
    public static void resetGameSum() {
        //determine whose turn it is
        switch (turn) {
            //reset the player sum to zero and print to console
            case player:
                System.out.print("TURN OVER! Setting player's game sum to zero!\n");
                playerSum = 0;
                break;
            //reset the computer sum to zero and print to console
            case computer:
                System.out.print("TURN OVER! Setting computer's game sum to zero!\n");
                computerSum = 0;
                break;
        }
    }

    /**
     * Resets either the turn sum for whoever's turn it is.
     * Also prints a message to the console to let the player know that the turn sum is being set to zero and their turn will be over.
     */
    public static void resetTurnSum() {
        //subtract the turn sum from the game sum
        subtractFromGameSum(turnSum);
        //determine whose turn it is
        switch (turn) {
            //reset the player sum to zero and print to console
            case player:
                System.out.print("TURN OVER! Setting player's turn sum to zero!\n");
                break;
            //reset the computer sum to zero and print to console
            case computer:
                System.out.print("TURN OVER! Setting computer's turn sum to zero!\n");
                break;
        }
        //reset the turn sum
        turnSum = 0;
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

    ///////////////////////////
    /* DICE ROLLING METHODS */
    //////////////////////////

    /**
     * Rolls a virtual dice and returns the dice number as an int.
     * @return A random number between 0 and DICE_SIDES as an int.
     * NOTE: DICE_SIDES constant is used as bound for random generator.
     */
    public static int rollDice() {
        //seed the random generator with the current time in milliseconds
        Random generator = new Random();
        //the string of the random number
        return generator.nextInt(DICE_SIDES+1);
    }

    /**
     * Will collect n (represented by NUMBER_ROLLS_PER_TURN) rolls and return an array full of ints.
     * @return An array with n (represented by NUMBER_ROLLS_PER_TURN) ints that represent the dice rolls.
     */
    public static int[] collectRolls(int numberOfRolls) {
        //create array of size numberOfRolls
        int[] rolls = new int[numberOfRolls];
        //add rolls into the array
        for (int i = 0; i < rolls.length; i++)
            rolls[i] = rollDice();
        //return the rolls array
        return rolls;
    }
}
