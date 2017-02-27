package library;

/**
 * Created by robertsaunders on 2017-02-25.
 */
public class Device extends Item {
    private int rentalCost;

    @Override
    public int getLateFees(int lateDays)
    {
        return 0;
    }

    @Override
    public Device clone() {
        //can use the copy constructor to clone the object

    }

    @Override
    public String toString() {

    }



}
