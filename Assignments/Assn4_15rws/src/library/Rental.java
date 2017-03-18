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

    //////////////////
    /* DEFINITIONS */
    ////////////////

    /**
     * Define the enum for the different statuses a rental can be in.
     */
    enum rentalStatus {
        active, //the rental transaction is created and the item is with the customer
        late, //the item is not returned by the estimated return Date/Time
        closed //the rental transaction is closed and the item is returned
    }

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //the rental identifier, i.e. transaction id
    private int id;
    //the item being rented
    private Item rentalItem;
    //the customer that is renting the item
    private Customer customer;
    //date at which the rental occurred
    private Date rentalDate;
    //the expected return date
    private Date expectedReturnDate;
    //the actual return date
    private Date actualReturnDate;
    //the status of the rental
    private rentalStatus status;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    /**
     * Rental constructor that has every attribute.
     * NOTE: This is a useful constructor if a rental needs to be entered into system after it has happened.
     * @param rentalItem The item that is being rented.
     * @param customer The customer renting the item.
     * @param expectedReturnDate The date that the item is expected to be returned.
     * @param actualReturnDate The date the item was actually returned.
     * @param status The status of the rental.
     */
    Rental(Item rentalItem, Customer customer, Date rentalDate, Date expectedReturnDate, Date actualReturnDate, rentalStatus status) {
        //set the item that is being rented
        setRentalItem(rentalItem);
        //set the customer
        setCustomer(customer);
        //set the date at which the rental occurred
        setRentalDate(rentalDate);
        //set the expected return date
        setExpectedReturnDate(expectedReturnDate);
        //set the actual return date
        setActualReturnDate(actualReturnDate);
        //set the rental status
        setStatus(status);
        //set the rental id
        incrementAndSetNewId();
    }

    /**
     * General constructor for a rental, will be used most.
     * NOTE: This one automatically sets status to active and does not set actual return date.
     * @param rentalItem The item being rented.
     * @param customer The customer renting the item.
     * @param rentalDate The date the item was rented.
     * @param expectedReturnDate The date the item is expected to be returned.
     */
    Rental(Item rentalItem, Customer customer, Date rentalDate, Date expectedReturnDate) {
        //set the item that is being rented
        setRentalItem(rentalItem);
        //set the customer
        setCustomer(customer);
        //set the date at which the rental occurred
        setRentalDate(rentalDate);
        //set the expected return date
        setExpectedReturnDate(expectedReturnDate);
        //set the rental id
        incrementAndSetNewId();
    }

    /**
     * Copy constructor for the rental class.
     * @param copyRental The rental that is to be copied.
     */
    Rental(Rental copyRental) {
        //check if passed copyRental is null
        if (copyRental == null) {
            System.out.print("Passing null object to copy, fatal error. [Rental --> Rental(Rental copyRental)]");
            System.exit(0);
        }
        //map copyRental values to the new rental instance
        //set the rental item
        setRentalItem(copyRental.getRentalItem());
        //set the customer
        setCustomer(copyRental.getCustomer());
        //set the rental date
        setRentalDate(copyRental.getRentalDate());
        //set the expected return date
        setExpectedReturnDate(copyRental.getExpectedReturnDate());
        //set the actual return date
        setActualReturnDate(copyRental.getActualReturnDate());
        //set the rental id, not copied, must be unique
        incrementAndSetNewId();
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the id of an item.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the rental item.
     * @param item The item that is to be rented.
     */
    public void setRentalItem(Item item) {
        this.rentalItem = item;
    }

    /**
     * Sets the customer.
     * @param customer The customer renting an item.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
     * Sets the actual return date of a rental.
     * @param actualReturnDate The actual return date of a rental.
     */
    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    /**
     * Sets the status of the rental.
     * @param status The rental status to be set.
     */
    public void setStatus(rentalStatus status) {
        this.status = status;
    }

    //******************//
    /* SPECIAL SETTER */
    //*****************//

    /**
     * Increments the idIncrementer class variable and sets the id for a new rental when no id is given.
     */
    private void incrementAndSetNewId() {
        //set the id to the incremented version of idIncrementer
        this.id = ++idIncrementer;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the id for the rental.
     * @return The id as an integer.
     */
    public int id() {
        return id;
    }

    /**
     * Gets the item that is being rented.
     * @return The item being rented.
     */
    public Item getRentalItem() {
        return rentalItem;
    }

    /**
     * Gets the customer renting the item.
     * @return The customer renting the item.
     */
    public Customer getCustomer() {
        return customer;
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

    /**
     * Gets the actual return date of the rental.
     * @return The actual return date of the rental.
     */
    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    /**
     * Gets the rental status of the rental.
     * @return The rental status.
     */
    public rentalStatus getStatus() {
        return status;
    }

    ////////////////////
    /* LOGIC GETTERS */
    //////////////////

    /**
     * Calculates the late fee based on the rental dates.
     * @return The late fee for the rental as a double.
     */
    public double getLateFee() {

    }

    /**
     * The rental cost for the rental, without late fee.
     * @return The rental cost of the rental, as a double.
     */
    public double getRentalCost() {

    }

    /**
     * The total amount to be paid by the customer, including any discounts.
     * @return The total amount to be paid by the customer including any discounts as a double.
     */
    public double getTotalToBePaid() {

    }


    //////////////////////////////
    /* STATUS MODIFIER METHODS */
    ////////////////////////////

    /**
     * Checks to see if a item is late, if it is changes the status.
     * @return True if the item is late, false otherwise.
     * NOTE: This is inclusive with rentalDate and expectedReturnDate.
     */
    public boolean isLate() {
        //create a date that represents the current date
        Date todayDate = new Date();
        //check if today's date is between expected return date and rental date, inclusive.
        if (!this.rentalDate.after(todayDate) && !this.expectedReturnDate.before(todayDate))
            return false;
        else {
            //update the status if the item is late
            setStatus(rentalStatus.late);
            //return true if the item is late
            return true;
        }
    }

    /**
     * Sets the actual return date of a rental and sets the rental status to closed.
     */
    public void itemReturned() {
        //set the actual return date to the current date
        setActualReturnDate(new Date());
        //set the status to be closed
        setStatus(rentalStatus.closed);
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
     * Overrides default equals method in Object class, compares rental identifiers.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Rental rental = (Rental)obj;
        return (this.getRentalId() == rental.getRentalId());
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
        //if the difference between the dates is negative then the rental is no late
        if (difference < 0.0) {
            return 0.0;
        }
        //otherwise return the difference
        return difference;
    }
}
