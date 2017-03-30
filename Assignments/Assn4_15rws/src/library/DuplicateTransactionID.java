/**
 * DuplicateTransactionID is an exception class that will be thrown when a rental ID already exists in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class DuplicateTransactionID extends Exception {

    //define the rental that has a duplicated id
    private Rental invalidTransaction;

    /**
     * Default constructor for the exception.
     */
    public DuplicateTransactionID() {
        super("Duplicate Transaction ID!");
    }

    /**
     * Constructor to send message to super class Exception.
     * @param message Message to be shared when the exception is thrown.
     */
    public DuplicateTransactionID(String message) {
        super(message);
    }

    /**
     * Constructor that takes in the rental that is not valid.
     * @param invalidTransaction The rental that is not valid.
     */
    public DuplicateTransactionID(Rental invalidTransaction) {
        super("Duplicate Transaction ID!");
        this.invalidTransaction = invalidTransaction;
    }

    /**
     * Gets the invalid item.
     * @return The invalid item.
     */
    public Rental getInvalidTransaction() {
        return invalidTransaction;
    }
}
