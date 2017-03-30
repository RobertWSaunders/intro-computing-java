package library;

/**
 * Created by robertsaunders on 2017-03-16.
 */
public class WrongRentalCostException extends Exception {

    public WrongRentalCostException() {
        super("Wrong rental cost!");
    }

    public WrongRentalCostException(String message) {
        super(message);
    }

}
