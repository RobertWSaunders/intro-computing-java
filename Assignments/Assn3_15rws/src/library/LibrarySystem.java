/**
 * LibrarySystem is a class represents the entire library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.ArrayList;

public class LibrarySystem {

    //////////////////////
    /* CLASS VARIABLES */
    ////////////////////

    //create a list to store rentals
    ArrayList rentalList = new ArrayList();

    ///////////////////////////
    /* SYSTEM LOGIC METHODS */
    /////////////////////////

    public void addTransaction(Item item) {
        rentalList.add(item);
    }

    public double getTotalLateFees() {
        return 0.0;
    }

    public double getTotalRentalCosts() {
        return 0.0;
    }

    /////////////////////////////
    /* TESTING LIBRARY SYSTEM */
    ///////////////////////////

    public static void main(String[] args) {
        //create a library system
        LibrarySystem librarySystem = new LibrarySystem();

        //create a customer who will be renting things
        Customer customer = new Customer();
        //the customer wants to rent a book
        //create a book

    }
}
