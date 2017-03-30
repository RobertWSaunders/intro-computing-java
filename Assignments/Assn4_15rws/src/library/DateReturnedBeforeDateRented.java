package library;

/**
 * Created by robertsaunders on 2017-03-16.
 */
public class DateReturnedBeforeDateRented extends Exception {

    public DateReturnedBeforeDateRented() {
        super("Date Returned Before Date Rented!");
    }

    public DateReturnedBeforeDateRented(String message) {
        super(message);
    }
}
