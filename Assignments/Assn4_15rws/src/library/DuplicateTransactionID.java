package library;

/**
 * Created by robertsaunders on 2017-03-16.
 */
public class DuplicateTransactionID extends Exception {

    public DuplicateTransactionID() {
        super("Duplicate Transaction ID!");
    }

    public DuplicateTransactionID(String message) {
        super(message);
    }
}
