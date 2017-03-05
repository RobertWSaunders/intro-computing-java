/**
 * Device inherits from the Item class and represents a device in the library system.
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

    ///////////////////////
    /* OVERRIDES */
    /////////////////////

    /**
     * Calculates the late fees based off of how many days the item is late.
     * @param lateDays The number of days the device is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return (2*Math.ceil(lateDays))+((int)(0.10*rentalCost));
    }




}
