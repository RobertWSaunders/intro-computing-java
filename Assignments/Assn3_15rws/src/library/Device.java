/**
 * Item is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

//part of the library pac
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
