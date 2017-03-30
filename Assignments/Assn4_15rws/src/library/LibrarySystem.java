/**
 * LibrarySystem is a class represents the entire library system.
 * Library system can be used to test the entire library package as it encapsulates all of it.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.HashMap;

public class LibrarySystem {

    /////////////////////////
    /* INSTANCE VARIABLES */
    ///////////////////////

    //create a list to store all unique items in list, the item id is the key
    private HashMap<Integer,Item> itemList;
    //create a list to store all customers in library system, the customer id is the key
    private HashMap<Integer,Customer> customerList;
    //create a list to store rentals, i.e. rental transactions, the rental id is the key
    private HashMap<Integer,Rental> rentalList;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor for the library system, initializes a empty lists.
     */
    LibrarySystem() {
        //set the lists in the system to new empty collections
        //set the rental list
        setRentalList(new HashMap<Integer,Rental>());
        //set the customer list
        setCustomerList(new HashMap<Integer,Customer>());
        //set the item list
        setItemList(new HashMap<Integer,Item>());
    }

    /**
     * Constructor to set all of the different lists in the library system.
     * @param rentalList The rental list to be set.
     * @param customerList The customer list to be set.
     * @param itemList The item list to be set.
     */
    LibrarySystem(HashMap<Integer,Rental> rentalList, HashMap<Integer,Customer> customerList, HashMap<Integer,Item> itemList) {
        //set the rental list
        setRentalList(rentalList);
        //set the customer list
        setCustomerList(customerList);
        //set the item list
        setItemList(itemList);
    }

    /**
     * Copy constructor for a library system.
     * @param copyLibrarySystem The library system to be copied.
     */
    LibrarySystem(LibrarySystem copyLibrarySystem) {
        //check if the copyLibrarySystem is null, cannot be copied if null
        if (copyLibrarySystem == null) {
            System.out.print("Passing null object to copy, fatal error. [LibrarySystem --> LibrarySystem(LibrarySystem copyLibrarySystem)]");
            System.exit(0);
        }
        //set the rental list
        setRentalList(copyLibrarySystem.getRentalList());
        //set the customer list
        setCustomerList(copyLibrarySystem.getCustomerList());
        //set the item list
        setItemList(copyLibrarySystem.getItemList());
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the rental list to the passed rental list.
     * @param rentalList The rental list to be set.
     */
    public void setRentalList(HashMap<Integer,Rental> rentalList) {
        this.rentalList = rentalList;
    }

    /**
     * Sets the customer list to the passed customer list.
     * @param customerList The customer list to be set.
     */
    public void setCustomerList(HashMap<Integer,Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * Sets the item list to the passed item list.
     * @param itemList The item list to be set.
     */
    public void setItemList(HashMap<Integer,Item> itemList) {
        this.itemList = itemList;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the rental list for the system.
     * @return The rental list for the system.
     */
    public HashMap<Integer,Rental> getRentalList() {
        return rentalList;
    }

    /**
     * Gets the customer list for the system.
     * @return The customer list for the system.
     */
    public HashMap<Integer,Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Gets the item list for the system.
     * @return The item list for the sytem.
     */
    public HashMap<Integer,Item> getItemList() {
        return itemList;
    }

    ///////////////////////////
    /* SYSTEM LOGIC METHODS */
    /////////////////////////

    /**
     * Adds an item to the library sytems item list.
     * @param item The item to be added to the item list.
     * @throws DuplicateItemID There cannot exist two items in the system with the same id.
     */
    public void addItem(Item item) throws DuplicateItemID {
        //check if the item with the same id already exists in the system
        if (itemList.containsKey(item.getId()))
            throw new DuplicateItemID(item);
        else
            //put the item into the item list, with the item id as the key
            itemList.put(item.getId(),item);
    }

    /**
     * Adds a rental to the library systems rental list.
     * @param rental The rental to be added to the rental list.
     */
    public void addTransaction(Rental rental) throws DuplicateTransactionID, DuplicateCustomerID {
        //check if a rental with the same id already exists in the system
        if (rentalList.containsKey(rental.getId()))
            throw new DuplicateTransactionID(rental);
        //check if a customer with the id already exists in the system
        else if (rentalList.containsKey(rental.getCustomer().getId()))
            throw new DuplicateCustomerID(rental.getCustomer());
        //proceed with registering the transaction if everything checks out
        else {
            //add the rental to the rental list, the rental id is the key
            rentalList.put(rental.getId(), rental);
            //add the customer who is renting to the customer list
            customerList.put(rental.getCustomer().getId(), rental.getCustomer());
        }
    }

    /**
     * Gets the total late fees that are owed to the library system.
     * @return The total late fees that are owed to the library system as a double.
     */
    public double getTotalLateFees() {
        //define the late fees double
        double totalLateFees = 0.0;
        //iterate through the library systems rental list
        for (Rental rental : rentalList.values()) {
            //add to the total late fees
            totalLateFees += rental.getRentalItem().getLateFees(rental.determineNumberOfDaysLate());
        }
        //return the total late fees
        return totalLateFees;
    }

    /**
     * Gets the total rental costs of the items being rented in the library system.
     * @return The total rental costs of items being rented in the library system as a double.
     * NOTE: Only devices can have a rental cost.
     */
    public double getTotalRentalCosts() {
        //define the total rental costs
        double totalRentalCosts = 0.0;
        //iterate through the rentals in the system
        for (Rental rental : rentalList.values()) {
            //only devices have a rental cost so check if the item is an instance of Device
            if (rental.getRentalItem() instanceof Device) {
                //add to the total rental costs
                totalRentalCosts += ((Device) rental.getRentalItem()).getRentalCost();
            }
        }
        //return the total rental costs
        return totalRentalCosts;
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
    public LibrarySystem clone() {
        //call the library systems copy constructor
        return new LibrarySystem(this);
    }

    /**
     * Overrides default toString method in Object class, will print all of the rentals in the library system.
     * @return A string to represent the LibrarySystem object.
     */
    @Override
    public String toString() {
        //create a string builder
        StringBuilder builder = new StringBuilder();
        //add header to the string
        builder.append("\n*************LIBRARY SYSTEM RENTALS*************\n");
        //iterate through the rental list to print the rentals in the library system
        for (Rental rental : rentalList) {
            //print the rental id
            builder.append("\nRental ID: "+rental.getId()+"\n");
            //print the customer id for the rental
            builder.append("Rental Customer ID: "+rental.getCustomer().getId()+"\n");
            //print the rental item type
            builder.append("Rental Item Type: "+rental.getRentalItem().getClass().getSimpleName()+"\n");
            //print the rental item
            builder.append("Rental Item: "+rental.getRentalItem().toString());
            builder.append("\n");
        }
        //add the footer of the note
        builder.append("\n*************************************************\n");
        //return the built string
        return builder.toString();
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //cast the past object to of type library system
        LibrarySystem librarySystem = (LibrarySystem)obj;
        return (this == librarySystem);
    }

    /////////////////////////////
    /* TESTING LIBRARY SYSTEM */
    ///////////////////////////

    public static void main(String[] args) {

    }

}
