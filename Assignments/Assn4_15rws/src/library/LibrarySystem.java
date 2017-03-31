/**
 * LibrarySystem is a class represents the entire library system.
 * Library system can be used to test the entire library package as it encapsulates all of it.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;

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

    //////////////////////
    /* UTILITY METHODS */
    ////////////////////

    /**
     * Prints all of the items in the library system.
     */
    public void printAllItems() {
        //create a string builder
        StringBuilder builder = new StringBuilder();
        //add header to the string
        builder.append("\n*************LIBRARY SYSTEM ITEMS*************\n");
        //iterate through the rental list to print the rentals in the library system
        for (Item item : itemList.values()) {
            //print the item id
            builder.append("\nItem ID: "+item.getId()+"\n");
            //print the item name
            builder.append("Item Name: "+item.getName()+"\n");
        }
        //add the footer of the note
        builder.append("\n*************************************************\n");
        //print the string
        System.out.println(builder.toString());
    }

    /**
     * Prints all of the rentals in the library system.
     */
    public void printAllRentals() {
        //the toString method will by default print all the rentals so just call that
        System.out.println(this.toString());
    }

    public void printAllLateRentals() {
        //create a string builder
        StringBuilder builder = new StringBuilder();
        //add header to the string
        builder.append("\n*************LATE RENTAL ITEMS*************\n");
        //iterate through the rental list to print the rentals in the library system
        for (Rental rental : rentalList.values()) {
            //note that calling isLate() will modify the status of a closed rental as well, if an item is late then it will always stay late.
            //this could be improved by not having late as a status but rather a boolean attribute, therefore closed rentals could still be seen as late.
            if (rental.isLate()) {
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
        }
        //add the footer of the note
        builder.append("\n*************************************************\n");
        //print the string
        System.out.println(builder.toString());
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
        for (Rental rental : rentalList.values()) {
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
        //create a library system that we will be testing
        LibrarySystem queensLibrary = new LibrarySystem();

        //create a customer who wants to rent some items
        //NOTE: You can toggle the customer type to employee and you will see that the student discount works
        Customer customer = new Customer("Robert Saunders", Customer.type.STUDENT,"Unspecified");

        //create a few items that the customer wants to rent
        Adaptor adaptor = new Adaptor("VGA Connector", 10);
        Textbook textbook = new Textbook(3,"Ender's Game");
        //using the id constructor
        Laptop laptop = new Laptop(2, "MacBook");

        //add these items to the library system
        try {
            //add the items into the
            queensLibrary.addItem(adaptor);
            //add the laptop
            queensLibrary.addItem(laptop);
            //add the textbook
            queensLibrary.addItem(textbook);
        }
        catch (DuplicateItemID e) {
            //retrieve the invalid item from the exception
            //not going to do anything with it but just demonstrating that I can retrieve it
            Item badItem = e.getInvalidItem();
            //print the exception error to the console
            System.out.println(e.getMessage());
        }

        //print the items in the library system
        queensLibrary.printAllItems();

        //create some old dates to use for testing purposes
        //create instance of a calendar
        final Calendar cal = Calendar.getInstance();
        //make a date in the past as the rental date
        cal.add(Calendar.DATE, -4);
        //put calendar into date
        Date rentalDate = cal.getTime();

        //print the return date for clarity
        System.out.println("Rental date is: " + rentalDate);

        //now make the expected return date
        cal.add(Calendar.DATE, +2);
        //put calendar into date
        Date expectedReturnDate = cal.getTime();

        //print the expected return date for clarity
        System.out.println("Expected Return date is: " + expectedReturnDate);

        //create a rental object for the item that the customer wants to rent
        //NOTE: not relating to the items already in the system because searching functions were moved to assignment #5
        Rental rental = new Rental(adaptor,customer,rentalDate,expectedReturnDate);

        //add this rental to the library system
        try {
            //add the items into the
            queensLibrary.addTransaction(rental);
        }
        catch (DuplicateTransactionID e) {
            //retrieve the invalid item from the exception
            //not going to do anything with it but just demonstrating that I can retrieve it
            Rental badRental = e.getInvalidTransaction();
            //print the exception error to the console
            System.out.println(e.getMessage());
        }
        catch (DuplicateCustomerID e) {
            //retrieve the invalid item from the exception
            //not going to do anything with it but just demonstrating that I can retrieve it
            Customer badCustomer = e.getInvalidCustomer();
            //print the exception error to the console
            System.out.println(e.getMessage());
        }

        //lets print the rentals in the library system
        queensLibrary.printAllRentals();

        //now lets try to return the rental (close)
        try {
            //return the item
            rental.itemReturned();
        } catch(DateReturnedBeforeDateRented e) {
            //print the exception error to the console
            System.out.println(e.getMessage());
        }

        //lets print the late fee associated with that rental
        System.out.println("Late Fee: $" + rental.getLateFee());
        //lets print out the total cost of that rental, including the discount on the rental cost
        //NOTE: getTotalToBePaid will handle the WrongRentalCost exception, so not account for in the main program
        System.out.println("Total Cost: $" + rental.getTotalToBePaid());

        //now lets print all of the late rentals
        //my implementation prints all late rentals regardless of their status, see comments in method
        //can easily be changed to just print open late rentals but instructions do not clarify
        queensLibrary.printAllLateRentals();

        //All done :)
    }
}
