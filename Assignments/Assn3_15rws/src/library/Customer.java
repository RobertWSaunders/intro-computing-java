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
    private ArrayList<Rental> customerRentals;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    /**
     * Default constructor for the customer class.
     */
    Customer() {
        //Set customer name to an unknown value
        setName("Unknown");
        //set the customer rentals to an empty list
        setCustomerRentals(new ArrayList<Rental>());
        //set the id of the customer
        setCustomerId();
    }

    Customer(String name) {
        //set the name of the customer
        setName(name);
        //set the customer rentals to an empty list
        setCustomerRentals(new ArrayList<Rental>());
        //set the customer id
        setCustomerId();
    }

    Customer(Customer copyCustomer) {
        if (copyCustomer == null) {
            //let the user know the error and tell them where it is in the code
            System.out.print("Passing null object to copy, fatal error. [Customer --> Customer(Customer copyCustomer)]");
            //exit the program as a result of a fatal error.
            System.exit(0);
        }
        setName(copyCustomer.name);
        setCustomerRentals(copyCustomer.customerRentals);
        setCustomerId();
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
    public void setCustomerId() {
        this.customerId = ++idIncrementer;
    }

    /**
     * Sets the customers rentals.
     * @param customerRentals The customer rentals to be set.
     */
    public void setCustomerRentals(ArrayList customerRentals) {
        this.customerRentals = customerRentals;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the id of the customer.
     * @return The id as an integer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the name of the customer.
     * @return The name as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customers rentals.
     * @return The customer rentals as a ArrayList..
     */
    public ArrayList getCustomerRentals() {
        return customerRentals;
    }


    ///////////////////////
    /* INSTANCE METHODS */
    /////////////////////

    /**
     * Will rent an item given the rental object and the library system being rented from.
     * Adds rental to the library systems rental list and the customers rental list.
     * @param rental The rental being rented.
     * @param librarySystem The library system from which the item is being rented from.
     */
    public void rent(Rental rental, LibrarySystem librarySystem) {
        //add the rental to the library system
        librarySystem.addTransaction(rental);
        //add the rental to the customers rentals
        addRentalToCustomerRentals(rental);
    }

    /**
     * Gets the customers late fees based on their rentals at the time.
     * @return The late fee for the customers rentals as a double.
     */
    public double getCustomersLateFees() {
        double lateFee = 0.0;
        for (Rental rental : customerRentals) {
            lateFee += rental.getRentalItem().getLateFees(rental.getNumDaysLate());
        }
        return lateFee;
    }

    /**
     * Gets the customers rental costs for the items they have rented.
     * @return The customers rental costs for items they are renting.
     * NOTE: Only devices can have a rental cost.
     */
    public double getCustomersRentalCosts() {
        double rentalCosts = 0.0;
        for (Rental rental : customerRentals) {
            if (rental.getRentalItem() instanceof Device) {
                rentalCosts += ((Device) rental.getRentalItem()).getRentalCost();
            }
        }
        return rentalCosts;
    }


    /**
     * Adds a rental to the customers rental list.
     * @param rental The item to be added to the customers rental list.
     */
    public void addRentalToCustomerRentals(Rental rental) {
        //add to the customer rentals ArrayList
        customerRentals.add(rental);
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
        return "\nCustomer Name: "+name+"\n"+"Customer ID: "+customerId;
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;
        return (this.getName() == customer.getName());
    }

    //////////////////////
    /* UTILITY METHODS */
    ////////////////////

    /**
     * Makes the customers rentals represented as a string.
     * @return The customers rentals as a string.
     */
    public String stringCustomerRentals() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n****************CUSTOMER RENTALS*****************\n");
        builder.append(toString() + "\n");
        if (customerRentals.size() == 0 ) {
            builder.append("\nCustomer has no rentals!\n");
        }
        else {
            for (Rental rental : customerRentals) {
                builder.append("\nRental ID: " + rental.getRentalId() + "\n");
                builder.append("Rental Item Type: " + rental.getRentalItem().getClass().getSimpleName() + "\n");
                builder.append("Rental Item: " + rental.getRentalItem().toString());
                builder.append("\n");
            }
        }
        builder.append("\n**************************************************\n");
        return builder.toString();
    }
}
