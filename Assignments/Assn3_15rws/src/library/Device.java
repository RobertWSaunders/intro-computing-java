/**
 * Device inherits from the Item class, i.e. is a subclass of Item, and represents a device in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

//part of the library package
package library;

//class definition
public class Device extends Item {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //define the rental cost for a device
    private double rentalCost = 10.0;

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the name of the item.
     * @param cost The rental cost of the device to be set.
     */
    public void setRentalCost(double cost) {
        this.rentalCost = cost;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the rental cost of a device.
     * @return The rental cost as an double.
     */
    public double getRentalCost() {
        return rentalCost;
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the device is late.
     * @param lateDays The number of days the device is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return (2*Math.ceil(lateDays))+(0.10*getRentalCost());
    }
}
