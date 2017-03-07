/**
 * Laptop inherits from the Device class, i.e. is a subclass of Device, and represents an laptop in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Laptop extends Device {

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the laptop is late.
     * @param lateDays The number of days the laptop is late.
     * @return The amount of fees that result because the laptop is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the laptop is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return (5*Math.ceil(lateDays))+(0.20*getRentalCost());
    }
}
