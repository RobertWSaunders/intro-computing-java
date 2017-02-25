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

}
