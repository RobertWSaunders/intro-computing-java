/**
 * DateReturnedBeforeDateRented is an exception class that will be thrown when an item is being return
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class DateReturnedBeforeDateRented extends Exception {

    /**
     * Default constructor for the exception.
     */
    public DateReturnedBeforeDateRented() {
        super("Date Returned Before Date Rented!");
    }

    /**
     * Constructor to send message to super class Exception.
     * @param message Message to be shared when the exception is thrown.
     */
    public DateReturnedBeforeDateRented(String message) {
        super(message);
    }
}
