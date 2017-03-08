/**
 * LibrarySystem is a class represents the entire library system.
 * Library system can be used to test the entire library package as it encapsulates all of it.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

import java.util.ArrayList;
import java.util.Date;

public class LibrarySystem {

    //////////////////////
    /* CLASS VARIABLES */
    ////////////////////

    //create a list to store rentals
    private ArrayList<Rental> rentalList;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor for the library system, initializes a empty rental list.
     */
    LibrarySystem() {
        setRentalList(new ArrayList<Rental>());
    }

    /**
     * Constructor to set a rental list.
     * @param rentalList The rental list to be set.
     */
    LibrarySystem(ArrayList rentalList) {
        //set the rental list
        setRentalList(rentalList);
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
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the rental list to the passed rental list.
     * @param rentalList The rental list to be set.
     */
    public void setRentalList(ArrayList<Rental> rentalList) {
        this.rentalList = rentalList;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the rental list for the system.
     * @return The rental list for the system.
     */
    public ArrayList<Rental> getRentalList() {
        return rentalList;
    }

    ///////////////////////////
    /* SYSTEM LOGIC METHODS */
    /////////////////////////

    /**
     * Adds a rental to the library systems rental list.
     * @param rental The rental to be added to the rental list.
     */
    public void addTransaction(Rental rental) {
        //add the rental to the rental list
        rentalList.add(rental);
    }

    /**
     * Gets the total late fees that are owed to the library system.
     * @return The total late fees that are owed to the library system as a double.
     */
    public double getTotalLateFees() {
        //define the late fees double
        double totalLateFees = 0.0;
        //iterate through the library systems rental list
        for (Rental rental : rentalList) {
            //add to the total late fees
            totalLateFees += rental.getRentalItem().getLateFees(rental.getNumDaysLate());
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
        for (Rental rental : rentalList) {
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
            builder.append("\nRental ID: "+rental.getRentalId()+"\n");
            //print the customer id for the rental
            builder.append("Rental Customer ID: "+rental.getCustomerId()+"\n");
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
        //create a library system to rent from
        LibrarySystem librarySystem = new LibrarySystem();

        //create a customer who will be renting things, this customer is named "Robert Saunders"
        Customer customer = new Customer("Robert Saunders");

        //the customer wants to rent a book
        //create a book that will be rented by the customer
        //create an array for the authors of the book
        String[] authors = new String[1];
        //Add the author to the array
        authors[0] = "Orson Scott Card";
        //create the instance of the book
        Book book = new Book("Ender's Game",authors,"Tor Books",1985);

        //create the rental for the book
        //how many days is the book being rented?
        int rentalDays = 5;
        //create the rental date, for testing purposes it is being rented today
        Date rentalDate = new Date();
        //create the rental instance
        Rental rental = new Rental(book,customer.getCustomerId(),rentalDays,rentalDate);

        //now let the customer rent the book
        customer.rent(rental,librarySystem);

        //print the customer to the console
        System.out.println(customer);
        //print the customers rental to the console
        System.out.println(rental);

        //now the customer wants to rent a laptop
        //create a laptop for the customer to rent, this MacBook Pro is $10 to rent
        Laptop myLaptop = new Laptop("MacBook Pro",10.0);

        //now create the rental for the customer
        Rental laptopRental = new Rental(myLaptop,customer.getCustomerId(),rentalDays,rentalDate);

        //now let the customer rent the laptop
        customer.rent(laptopRental,librarySystem);
        //now print the customers new laptop rental to the console
        System.out.println(laptopRental);

        //now lets print out all of the rentals in the library system
        System.out.println(librarySystem.toString());

        //now lets print out the customers rentals
        System.out.println(customer.stringCustomerRentals());

        //now lets print the customers total rental costs, should be 10.0 because the book has no rental cost
        System.out.println("Customer Rental Costs: "+customer.getCustomersRentalCosts());

        //now lets print the library systems total rental costs, should be 10.0 because only the laptop has a rental cost
        System.out.println("Library System Rental Costs: "+librarySystem.getTotalRentalCosts());

        //now lets print the customers total rental costs, should be 0 because no rental is late
        System.out.println("Customer Rental Late Fees: "+customer.getCustomersLateFees());

        //now lets print the library systems rental costs, should be 0 because no rentals are late
        System.out.println("Library System Rental Costs: "+librarySystem.getTotalLateFees());

        //now lets create another customer
        Customer customer1 = new Customer("Johnny Appleseed");
        //now lets print out the customers rentals
        System.out.println(customer1.stringCustomerRentals());

        //now the new customer wants to rent an adaptor
        //lets create an adaptor, this adaptor costs $5
        Adaptor adaptor = new Adaptor("MacBook Charger",5.0);
        //now create the rental for the customer
        Rental rental1 = new Rental(adaptor,customer1.getCustomerId(),rentalDays,rentalDate);
        //now let the customer rent it
        customer1.rent(rental1,librarySystem);

        //now print the customers rentals
        System.out.println(customer1.stringCustomerRentals());

        //now lets print out all of the rentals in the library system
        System.out.println(librarySystem.toString());

        //now the customer wants another exact same adaptor
        //lets create that adaptor using the copy constructor
        Adaptor adaptor1 = new Adaptor(adaptor);
        //now lets create another rental for the customer
        Rental rental2 = new Rental(adaptor1,customer1.getCustomerId(),rentalDays,rentalDate);
        //now let the customer rent it
        customer1.rent(rental2,librarySystem);

        //now print the customers rentals
        System.out.println(customer1.stringCustomerRentals());

        //now lets print out all of the rentals in the library system
        System.out.println(librarySystem.toString());

        //now the library wants to back up its system so it wants to make a clone
        //lets clone it using the overridden method
        LibrarySystem backupSystem = librarySystem.clone();
        //lets print the backup system to show its the same
        System.out.println(backupSystem.toString());

        //now my manager suspects a customer is making a duplicate in the system, lets check if customer is equal to customer1
        System.out.println("Are there duplicate customers? " +customer.equals(customer1));

        //All done, hope I demonstrated enough. :)
    }
}
