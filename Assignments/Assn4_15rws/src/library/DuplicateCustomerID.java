package library;

/**
 * Created by robertsaunders on 2017-03-16.
 */
public class DuplicateCustomerID extends Exception {

    public DuplicateCustomerID() {
        super("Duplicate Customer ID!");
    }

    public DuplicateCustomerID(String message) {
        super(message);
    }

}
