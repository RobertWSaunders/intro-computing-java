/**
 * Rental is a class that represents a rental within the library system.
 * NOTE: In my version I have added a rentalDate and rentalExpectedReturnDate so I can see if item that is being rented is late.
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
    //the number of days the rental late
    private double numDaysLate;

    //NOTE: Extensions to assignment
    //makes sense to have a rental id
    private int rentalId;
    //date at which the rental occurred
    private Date rentalDate;
    //the expected return date
    private Date expectedReturnDate;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    /**
     * Rental constructor that has every attribute.
     * @param rentalItem The item that is being rented.
     * @param customerId The identifier of the customer renting the item.
     * @param numRentalDays The number of days the item is to be rented for.
     * @param rentalDate The date the rental occurred.
     */
    Rental(Item rentalItem, int customerId, int numRentalDays, Date rentalDate) {
        //set the item that is being rented
        setRentalItem(rentalItem);
        //set the id of the customer
        setCustomerId(customerId);
        //set the number of rental days
        setNumRentalDays(numRentalDays);
        //set the date at which the rental occurred
        setRentalDate(rentalDate);
        //set and determine the expected return date of the rental
        setExpectedReturnDate(determineExpectedReturnDate(rentalDate,numRentalDays));
        //set and determine the number days the rental is late
        //note this will always most likely be zero but give flexibility to create a rental that may have not been logged at the time of rental
        setNumDaysLate(getExpectedReturnDate());
        setRentalId();
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
        setNumDaysLate(copyRental.getExpectedReturnDate());
        setNumRentalDays(copyRental.numRentalDays);
        setCustomerId(copyRental.customerId);
        setRentalDate(copyRental.rentalDate);
        setExpectedReturnDate(copyRental.expectedReturnDate);
        setRentalId();
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
     * Sets the number of days the item is late base on its rental date and expected return date.
     * @param expectedReturnDate The the day the item is to be returned.
     */
    public void setNumDaysLate(Date expectedReturnDate) {
        this.numDaysLate = determineNumberOfDaysLate(expectedReturnDate,getRentalDate());
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

    /**
     * Sets the id of the rental by incrementing the idIncrementer variable.
     */
    public void setRentalId() {
        this.rentalId = ++idIncrementer;
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
    public double getNumDaysLate() {
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

    public int getRentalId() {
        return rentalId;
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Rental clone() {
        return new Rental(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Rental object.
     */
    @Override
    public String toString() {
        return "\nRental Type: "+ rentalItem.getClass().getSimpleName()+"\n"+"Rental Length: "+numRentalDays+" Days"+"\n"+rentalItem.toString();
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Rental rental = (Rental)obj;
        return (this == rental);
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

    /**
     * Determines the number of days between two dates but specifically determines number of days a rental is late.
     * NOTE: This function does not account for daylight savings and is not accurate to a calendar but demonstrates concept.
     * IMPROVEMENT: Could be modified to work for daylight savings and calendar, would use JodaTime API.
     * @param rentalDate The date at which the rental occurred.
     * @param expectedReturnDate The expected return date of the rental.
     * @return The number of days between the dates.
     */
    public static double determineNumberOfDaysLate(Date expectedReturnDate, Date rentalDate) {
        double difference = (rentalDate.getTime() - expectedReturnDate.getTime()) / (1000 * 60 * 60 * 24);
        if (difference < 0.0) {
            return 0.0;
        }
        return difference;
    }
}
