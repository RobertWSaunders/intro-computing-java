/**
 * WrongRentalCostException is an exception class that will be thrown when an improper rental cost is found.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class WrongRentalCostException extends Exception {

    /**
     * Default constructor for the exception.
     */
    public WrongRentalCostException() {
        super("This is not a viable rental cost!");
    }

    /**
     * Constructor to send message to super class Exception.
     * @param message Message to be shared when the exception is thrown.
     */
    public WrongRentalCostException(String message) {
        super(message);
    }
}
