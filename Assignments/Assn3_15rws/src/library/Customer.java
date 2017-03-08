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

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Customer clone() {
        return new Customer(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Device object.
     */
    @Override
    public String toString() {
        return name+" "+"["+customerId+"]";
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;
        return (this == customer);
    }

}
