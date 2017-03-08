/**
 * Customer is a class that represents a customer who would use the library system.
 * NOTE: This class is an extension to the assignment.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.ArrayList;

public class Customer {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //the name of the customer
    private String name;
    //the id of the customer
    private int customerId;
    //the rentals the customer has
    private ArrayList customerRentals;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    Customer() {

    }



    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the name of the item.
     * @param name The name of the item to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the id of the customer by incrementing the idIncrementer variable.
     */
    public void setCustomerIdId() {
        this.customerId = idIncrementer++;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the id of the item.
     * @return The id as an integer.
     */
    public int getCustomerIdId() {
        return customerId;
    }

    /**
     * Gets the name of the item.
     * @return The name as a string.
     */
    public String getName() {
        return name;
    }

}
