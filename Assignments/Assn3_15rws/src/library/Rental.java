/**
 * Rental is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.Date;

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
    public void numDaysLate(int daysLate) {
        this.numDaysLate = daysLate;
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


}
