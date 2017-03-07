/**
 * Adapter inherits from the Device class, i.e. is a subclass of Device, and represents an adapter in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public final class Adaptor extends Device {

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the adapter is late.
     * @param lateDays The number of days the adapter is late.
     * @return The amount of fees that result because the adapter is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the adapter is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return (2.5*Math.ceil(lateDays))+(0.15*getRentalCost());
    }

}
