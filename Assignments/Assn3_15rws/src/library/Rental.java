/**
 * Rental is a class that represents a rental within the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.Date;
import java.util.Calendar;

public class Rental {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //the item being rented
    private Item rentalItem;
    //the customer id that is renting the item
    private int customerId;
    //the number of rental days
    private int numRentalDays;
    //the number of days late
    private int numDaysLate;

    //NOTE: Extensions to assignment
    //date at which the rental occurred
    private Date rentalDate;
    //the expected return date
    private Date expectedReturnDate;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Rental constructor that has every attribute.
     * @param rentalItem The item that is being rented.
     * @param customerId The identifier of the customer renting the item.
     * @param numRentalDays The number of days the item is to be rented for.
     * @param numDaysLate The number of days the item is late.
     */
    Rental(Item rentalItem, int customerId, int numRentalDays, int numDaysLate, Date rentalDate) {
        setRentalItem(rentalItem);
        setCustomerId(customerId);
        setNumRentalDays(numRentalDays);
        setNumDaysLate(numDaysLate);
        setRentalDate(rentalDate);
        setExpectedReturnDate(determineExpectedReturnDate(rentalDate,numRentalDays));
    }

    /**
     * Copy constructor for the rental class.
     * @param copyRental The rental that is to be copied.
     */
    Rental(Rental copyRental) {
        if (copyRental == null) {
            System.out.print("Passing null object to copy, fatal error. [Rental --> Rental(Rental copyRental)]");
            System.exit(0);
        }
        setRentalItem(copyRental.rentalItem);
        setNumDaysLate(copyRental.numDaysLate);
        setNumRentalDays(copyRental.numRentalDays);
        setCustomerId(copyRental.customerId);
        setRentalDate(copyRental.rentalDate);
        setExpectedReturnDate(copyRental.expectedReturnDate);
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the rental item.
     * @param item The item that is to be rented.
     */
    public void setRentalItem(Item item) {
        this.rentalItem = item;
    }

    /**
     * Sets the customer id.
     * @param id The id of the customer renting an item.
     */
    public void setCustomerId(int id) {
        this.customerId = id;
    }

    /**
     * Sets the number of rental days for the item being rented.
     * @param numRentalDays The number of days the item is to be rented for.
     */
    public void setNumRentalDays(int numRentalDays) {
        this.numRentalDays = numRentalDays;
    }

    /**
     * Sets the number of days the item is late.
     * @param daysLate The number of days the item is late.
     */
    public void setNumDaysLate(int daysLate) {
        this.numDaysLate = daysLate;
    }

    /**
     * Sets the rental date.
     * @param rentalDate The start date of the rental.
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * Sets the expected return date of a rental.
     * @param returnDate The expected return date of a rental.
     */
    public void setExpectedReturnDate(Date returnDate) {
        this.expectedReturnDate = returnDate;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the item that is being rented.
     * @return The item being rented.
     */
    public Item getRentalItem() {
        return rentalItem;
    }

    /**
     * Gets the customer id of the customer renting the item.
     * @return The identifier of the customer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the number of rental days.
     * @return The number of rental days.
     */
    public int getNumRentalDays() {
        return numRentalDays;
    }

    /**
     * Gets the number of days the item is late.
     * @return The number of days the item late.
     */
    public int getNumDaysLate() {
        return numDaysLate;
    }

    /**
     * Gets the date the item was rented.
     * @return The date at which was rented.
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * Gets the expected return date of the rental.
     * @return The expected return date of the rental.
     */
    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    //////////////
    /* GETTERS */
    ////////////

    @Override
    public Magazine clone() {
        return new Magazine(this);
    }

    @Override
    public String toString() {
        return super.toString()+" [Magazine]";
    }

    @Override
    public boolean equals(Object obj) {
        Magazine magazine = (Magazine) obj;
        return (this == magazine);
    }

    ////////////////////////////
    /* CLASS UTILITY METHODS */
    //////////////////////////

    /**
     * Calculates the expected return date of a rental based on rental date and rental length.
     * @param rentalDate The day the rental started.
     * @param rentalLength The length of the rental.
     * @return The expected return date of the rental.
     */
    public static Date determineExpectedReturnDate(Date rentalDate, int rentalLength) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentalDate);
        calendar.add(Calendar.DAY_OF_MONTH, rentalLength);
        return calendar.getTime();
    }


}
