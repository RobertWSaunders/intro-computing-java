/**
 * Item is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

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
