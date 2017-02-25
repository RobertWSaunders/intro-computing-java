package library;

/**
 * Created by robertsaunders on 2017-02-25.
 */
public class Book extends Item {

    private String[] authors;
    private String publisher;
    private int year;


    @Override
    public int getLateFees(int lateDays)
    {
        return 0;
    }


}
