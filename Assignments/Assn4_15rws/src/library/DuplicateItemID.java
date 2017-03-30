package library;

/**
 * Created by robertsaunders on 2017-03-16.
 */
public class DuplicateItemID extends Exception {

    public DuplicateItemID() {
        super("Duplicate Item ID!");
    }

    public DuplicateItemID(String message) {
        super(message);
    }
}
