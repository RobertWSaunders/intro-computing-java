/**
 * The purpose of this assignment is to practice designing methods, using loops, conditionals and console I/O.
 * This program allows a user to play against a computer in the two dice variation of the Game of Pig. This
 * implementation is configurable to play the n dice variation (default 2), a larger winning score (default
 * 100) and even an n sided dice (default 6).
 *
 * Game Rules:
 * The first player to accumulate a score of WINNING_SCORE wins.
 * The human goes first.
 * After one roll, a player has the choice to "hold" or to roll again.
 * If both dice are ones, then your turn is over and your accumulated score is set to zero (ouch!).
 * If one dice is one, then your turn is over and your turn score is set to zero.
 * If both dice match ("doubles") then you must roll again.
 * For any other dice combination, you just add the dice total to your turn score and you have the choice of rolling again.
 * When your turn is over, either through your choice or you rolled a one, then your turn sum is added to your accumulated score.
 *
 * The computer will continue to roll as long as its turn sum is less than or equal to COMPUTER_TURN_SUM_BOUND (default 30)
 * or the game rules do not permit it to continue to roll.
 *
 * @author Robert Saunders (10194030)
 * @version 1.0.0
 */

//used to generate a random number
import java.util.Random;
//used to catch exceptions when asking users for input
import java.io.IOException;
//used to sum items in dice array
import java.util.stream.*;

public class Assn1_15rws {

    //define the score needed to win the game
    private static final int WINNING_SCORE = 100;

    //define the size of dice you want to play the game with
    //NOTE: Only die with greater than 0 and six or less sides will work for current implementation
    private static final int DICE_SIDES = 6;
    //define the number of rolls that a user could take per turn
    private static final int NUMBER_ROLLS_PER_TURN = 2;
    //define the amount of turn sum the computer can accumulate before ending a turn
    private static final int COMPUTER_TURN_SUM_BOUND = 30;
    //define the amount of time that you want as a delay between computer actions, to prevent from jarring output (seconds)
    private static final int COMPUTER_SEQUENCE_DELAY = 1;

    //define the different type of turns
    private enum Turn {
        player, //player
        computer //computer
    }

    //define the different dice combinations either the computer or player could roll
    private enum DiceCombination {
        snakeeyes, //snakeeyes, when all dice are ones
        singleone, //singleone, if any of the dice are one
        matching, //matching, when all dice are matching
        random //random, when all dice are different
    }

    //define the different types of user input we need from the user
    private enum UserInputType {
        rollAgain, //when we ask if the user wants to roll again
        startRound //when we tell the user to press <space><enter> to start a new round
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
     * Main method of the program, checks if game has been won, if so prints the winner, otherwise begins a round.
     * @param args Arguments passed at compile time, not being used.
     */
    public static void main(String[] args) {
        // play the game as long as no has won the game
        while (!gameComplete()) {
            //if no one has won then begin round
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
    private static void beginRound() {
        //only increment the round number if the next turn is the player's
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
    private static void endRound() {
        //wait till the user wants to start a new round
        if (startNewRound()) {
            //switch turns at the end of the round
            switchTurns();
        }
    }

    /**
     * Begins a turn.
     */
    private static void beginTurn() {
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
    private static void endTurn() {
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
     * Determines if the user or computer can or want to roll again.
     * @param rolls the rolls for the current turn
     * @return True if the user does want to roll again, false otherwise.
     */
    private static boolean rollAgain(int[] rolls) {
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
                    //if the dice are matching tell the user they must roll again
                    if (combination == DiceCombination.matching) {
                        printMatchingMustRoll();
                        return true;
                    }
                    //check if the computer has exceeded the turn sum
                    if (turn == Turn.computer && turnSum >= COMPUTER_TURN_SUM_BOUND) {
                        return false;
                    }
                    return true;
                }
        }
        //by default return false if game rules do not permit them to roll again, or the game has been won
        return false;
    }

    /**
     * Determines if the player or computer is allowed to roll by comparing what they rolled against the game rules.
     * @param rolls An array full of ints that represent the rolls.
     * @param combination The combination the dice are in.
     * @return True if the user is allowed to roll again, false otherwise.
     */
    private static boolean isAllowedToRollAgain(int[] rolls, DiceCombination combination) {
        //set the roll sum by calculating it
        int rollSum = calculateRollSum(rolls);
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

    /**
     * Returns the combination that the dice are in.
     * @param rolls An array full of ints that represent the rolls.
     * @return The correct DiceCombination enum for the given dice combination.
     */
    private static DiceCombination getDiceCombination(int[] rolls) {
        //must use a for loop because can be n number of rolls
        //checks first case, are all rolls the same
        //flag to keep track of the dice, will be set to false if any dice do not equal
        boolean matchingFlag = true;
        //checks third case, if rolls all match
        for(int i=1; i<rolls.length; i++){
            //check if all rolls match
            if(rolls[0] != rolls[i])
                //set flag to false if dice dont all match
                matchingFlag = false;
        }
        //check if the flag is still true
        if (matchingFlag == true) {
            //if the rolls match are equal to one then it has to be snakeeyes
            if (rolls[0] == 1) {
                return DiceCombination.snakeeyes;
            }
            //otherwise its just the matching combination
            return DiceCombination.matching;
        }
        //checks second case, if any rolls equal one
        for(int roll : rolls){
            //check if roll equals 1
            if(roll == 1)
                //return the singleone combination
                return DiceCombination.singleone;
        }
        //otherwise return the random combination
        return DiceCombination.random;
    }


    /**
     * Prompts user with the passed message and depending on the UserInputType will respond with a boolean.
     * @param message message to prompt the user with
     * @param type the type of input we are looking for, UserInputType
     * @return True if the user does want to roll again, false otherwise.
     */
    private static boolean getUserInput(String message, UserInputType type) {
        //never ask the computer if its wants to roll again, defaults to true
        //only ask the user if they want to roll again if the dice are random
        boolean inputOk = false;
        //create array of type byte, holds one bytes, only looking for one response
        //much more efficient than using the Scanner class
        byte[] buffer = new byte[1];
        //ask the user if they want to roll again
        System.out.printf("%s ",message);
        //the system will keep just waiting until a valid response is inputted
        while (!inputOk) {
            //try catch block to handle exception
            try {
                //returns number of bytes in buffer
                System.in.read(buffer);
            } catch (IOException e) {
                //output to user if an exception is caught
                System.out.println("Should not have got here!");
            }
            //if the prompt is to see if the user wants to roll again
            if (type == UserInputType.rollAgain) {
                if ((char) buffer[0] == 'y') {
                    //if yes then return true
                    return true;
                } else if ((char) buffer[0] == 'n') {
                    //if no then return false
                    return false;
                }
            }
            //if the prompt is to see if the user wants to start a new round
            if (type == UserInputType.startRound) {
                //look for the space character
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
    private static void printWinner() {
        //check to see if the computer sum is greater than the player sum
        if (computerSum > playerSum) {
            //therefore the computer has won
            System.out.print("Computer Wins!");
        }
        else {
            //otherwise the player has won
            System.out.print("Player Wins!");
        }
        //print game over
        System.out.print("\n\nGAME OVER!");
    }

    /**
     * Prints a roll in the format "Player rolled two + five", can support n rolls
     * @param rolls Rolls to be printed, can be n number of rolls.
     */
    private static void printRoll(int ... rolls) {
        //define a string builder
        StringBuilder stringBuilder = new StringBuilder();
        //append corresponding string depending on whose turn it is
        switch (turn) {
            case player:
                stringBuilder.append("Player rolled ");
                break;
            case computer:
                //delay so computer output isn't jarring to the user
                delay();
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
    private static void printWhoseTurn() {
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

    /**
     * Prints a message saying that whoever's turn it is has to roll again, happens when matching rolls occur.
     */
    private static void printMatchingMustRoll() {
        //determine whose turn it is
        switch (turn) {
            case player:
                System.out.print("Player must roll again!\n");
                break;
            case computer:
                //delay so computer output isn't jarring to the user
                delay();
                System.out.print("Computer must roll again!\n");
                break;
        }
    }

    /**
     * Prints the turn summary in the format "Player's turn sum is: 6 and game sum is: 6"
     */
    private static void printTurnSum() {
        //determine whose turn it is
        switch (turn) {
            case player:
                //print players version
                System.out.printf("Player's turn sum is: %d and game sum is: %d.\n",turnSum,playerSum);
                break;
            case computer:
                //delay so computer output isn't jarring to the user
                delay();
                //print computer version
                System.out.printf("Computer's turn sum is: %d and game sum is: %d.\n",turnSum,computerSum);
                break;
        }
    }

    /**
     * Prints a turns summary in the format "Player's sum is: 4, Computer's sum is: 10."
     */
    private static void printTurnSummary() {
        System.out.printf("\nPlayer's sum is: %d, Computer's sum is: %d.\n",playerSum,computerSum);
    }

    /**
     * @return True when user puts the correct input in.
     */
    private static boolean startNewRound() {
        return getUserInput(String.format("\nPlayer's sum is: %d, Computer's sum is: %d. Press <space><enter> to start round %d.",playerSum,computerSum, round), UserInputType.startRound);
    }

    ///////////////////////////
    /* GAME UTILITY METHODS */
    /////////////////////////

    /**
     * Checks if the game is complete based off of the WINNING_SCORE.
     * @return True if the game has been won, false otherwise, does not tell us who won.
     */
    private static boolean gameComplete() {
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
    private static void switchTurns() {
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
    private static void addToGameSum(int amountAdded) {
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
    private static void subtractFromGameSum(int amountSubtracted) {
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
    private static int calculateRollSum(int[] rolls) {
        //add sum of rolls to get the roll sum
        return IntStream.of(rolls).sum();
    }

    /**
     * Resets either the player game sum or the game sum to zero based off of whose turn it is.
     * Also prints a message to the console to let the player know a game sum is being set to zero and their turn will be over.
     */
    private static void resetGameSum() {
        //determine whose turn it is
        switch (turn) {
            //reset the player sum to zero and print to console
            case player:
                System.out.print("TURN OVER! Setting player's game sum to zero!\n");
                playerSum = 0;
                break;
            //reset the computer sum to zero and print to console
            case computer:
                delay();
                System.out.print("TURN OVER! Setting computer's game sum to zero!\n");
                computerSum = 0;
                break;
        }
    }

    /**
     * Resets either the turn sum for whoever's turn it is.
     * Also prints a message to the console to let the player know that the turn sum is being set to zero and their turn will be over.
     */
    private static void resetTurnSum() {
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
                delay();
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
    private static String numToString(int num) {
        //switch statement for passed num
        switch(num) {
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
     * Delays time for COMPUTER_SEQUENCE_DELAY seconds.
     * Used to make the computers output not jarring.
     */
    private static void delay() {
        try {
            Thread.sleep(COMPUTER_SEQUENCE_DELAY*1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    ///////////////////////////
    /* DICE ROLLING METHODS */
    //////////////////////////

    /**y
     *
     * Rolls a virtual dice and returns the dice number as an int.
     * @return A random number between 1 and DICE_SIDES as an int.
     * NOTE: DICE_SIDES constant is used as bound for random generator.
     */
    private static int rollDice() {
        //because computer can call this method very fast because it has to roll again seeded with current
        //time in millisecond is not viable, actually results in same rolls
        Random generator = new Random();
        //the string of the random number
        return generator.nextInt(DICE_SIDES)+1;
    }

    /**
     * Will collect n (represented by NUMBER_ROLLS_PER_TURN) rolls and return an array full of ints.
     * @return An array with n (represented by NUMBER_ROLLS_PER_TURN) ints that represent the dice rolls.
     */
    private static int[] collectRolls(int numberOfRolls) {
        //create array of size numberOfRolls
        int[] rolls = new int[numberOfRolls];
        //add rolls into the array
        for (int i = 0; i < rolls.length; i++)
            rolls[i] = rollDice();
        //return the rolls array
        return rolls;
    }
}
