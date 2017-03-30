/**
 * DuplicateItemID is an exception class that will be thrown when an item ID already exists in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */


package library;

public class DuplicateItemID extends Exception {

    //define the item that has a duplicated id
    private Item invalidItem;

    /**
     * Default constructor for the exception.
     */
    public DuplicateItemID() {
        super("Duplicate Item ID!");
    }

    /**
     * Constructor to send message to super class Exception.
     * @param message Message to be shared when the exception is thrown.
     */
    public DuplicateItemID(String message) {
        super(message);
    }

    /**
     * Constructor that takes in the item that is not valid.
     * @param invalidItem The item that is not valid.
     */
    public DuplicateItemID(Item invalidItem) {
        super("Duplicate Item ID!");
        this.invalidItem = invalidItem;
    }

    /**
     * Gets the invalid item.
     * @return The invalid item.
     */
    public Item getInvalidItem() {
        return invalidItem;
    }
}
