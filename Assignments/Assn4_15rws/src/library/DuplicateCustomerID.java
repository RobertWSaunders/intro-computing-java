/**
 * DuplicateCustomerID is an exception class that will be thrown when a customer ID already exists in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class DuplicateCustomerID extends Exception {

    //define the customer that has a duplicated id
    private Customer invalidCustomer;

    /**
     * Default constructor for the exception.
     */
    public DuplicateCustomerID() {
        super("Duplicate Customer ID!");
    }

    /**
     * Constructor to send message to super class Exception.
     * @param message Message to be shared when the exception is thrown.
     */
    public DuplicateCustomerID(String message) {
        super(message);
    }

    /**
     * Constructor that takes in the customer that is not valid.
     * @param invalidCustomer The customer that is not valid.
     */
    public DuplicateCustomerID(Customer invalidCustomer) {
        super("Duplicate Customer ID!");
        this.invalidCustomer = invalidCustomer;
    }

    /**
     * Gets the invalid customer.
     * @return The invalid customer.
     */
    public Customer getInvalidCustomer() {
        return invalidCustomer;
    }

}
