/**
 * LibrarySystem is a class represents the entire library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.ArrayList;
import java.util.Date;

public class LibrarySystem {

    //////////////////////
    /* CLASS VARIABLES */
    ////////////////////

    //create a list to store rentals
    private ArrayList<Rental> rentalList;

    ///////////////////////////
    /* SYSTEM LOGIC METHODS */
    /////////////////////////

    /**
     * Adds a rental to the library systems rental list.
     * @param rental The rental to be added to the rental list.
     */
    public void addTransaction(Rental rental) {
        //add the rental to the rental list
        rentalList.add(rental);
    }

    /**
     * Gets the total late fees that are owed to the library system.
     * @return The total late fees that are owed to the library system as a double.
     */
    public double getTotalLateFees() {
        //define the late fees double
        double totalLateFees = 0.0;
        //iterate through the library systems rental list
        for (Rental rental : rentalList) {
            //add to the total late fees
            totalLateFees += rental.getRentalItem().getLateFees(rental.getNumDaysLate());
        }
        //return the total late fees
        return totalLateFees;
    }

    /**
     * Gets the total rental costs of the items being rented in the library system.
     * @return The total rental costs of items being rented in the library system as a double.
     * NOTE: Only devices can have a rental cost.
     */
    public double getTotalRentalCosts() {
        //define the total rental costs
        double totalRentalCosts = 0.0;
        for (Rental rental : rentalList) {
            //only devices have a rental cost so check if the item is an instance of Device
            if (rental.getRentalItem() instanceof Device) {
                totalRentalCosts += ((Device) rental.getRentalItem()).getRentalCost();
            }
        }
        //return the total rental costs
        return totalRentalCosts;
    }

    /////////////////////////////
    /* TESTING LIBRARY SYSTEM */
    ///////////////////////////

    public static void main(String[] args) {
        /*
        //create a library system to rent from
        LibrarySystem librarySystem = new LibrarySystem();
        //create a customer who will be renting things
        Customer customer = new Customer();
        //the customer wants to rent a book
        //create a book that will be rented by the customer
        Book book = new Book();
        //create the rental for the book
        Rental rental = new Rental();
        //let the customer rent the book
        customer.rent(rental,librarySystem);
        */
        Date date = new Date();

        System.out.print(Rental.determineNumberOfDaysLate(Rental.determineExpectedReturnDate(date, 4),date));
    }
}
