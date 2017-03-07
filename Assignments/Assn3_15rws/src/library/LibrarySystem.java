/**
 * Item is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

//import the array list class
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LibrarySystem {

    //////////////////////
    /* CLASS VARIABLES */
    ////////////////////

    //create a list to store rentals
    ArrayList rentalList = new ArrayList();

    ///////////////////////////
    /* SYSTEM LOGIC METHODS */
    /////////////////////////

    public void addTransaction() {
        Rental rental = new Rental();
        rentalList.add(rental);
    }

    public double getTotalLateFees() {
        return 0.0;
    }

    public double getTotalRentalCosts() {
        return 0.0;
    }

    /////////////////////////////
    /* TESTING LIBRARY SYSTEM */
    ///////////////////////////

    public static void main(String[] args) {

    }
}
