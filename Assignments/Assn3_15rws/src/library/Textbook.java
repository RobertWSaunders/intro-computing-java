/**
 * Textbook inherits from the Book class, i.e. is a subclass of Book, and represents an textbook in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */
package library;

public class Textbook extends Book {

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
        return 1*Math.ceil(lateDays);
    }
}
