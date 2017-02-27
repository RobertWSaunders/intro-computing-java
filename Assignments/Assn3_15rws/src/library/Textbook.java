package library;

/**
 * Created by robertsaunders on 2017-02-25.
 */

//final indicates that it cannot be used as a base class
public final class Textbook extends Book {

    @Override
    public int getLateFees(int lateDays)
    {
        return 0;
    }

}
