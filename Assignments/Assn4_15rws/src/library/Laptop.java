/**
 * Laptop inherits from the Device class, i.e. is a subclass of Device, and represents an laptop in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Laptop extends Device {

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Laptop constructor that has every attribute.
     * @param name Name of the laptop.
     * @param rentalCost Rental cost of the laptop.
     */
    Laptop(String name, double rentalCost) {
        //call superclass constructor
        super(name, rentalCost);
    }

    /**
     * Constructor that takes an id.
     * @param id The id to be set as the laptop id.
     */
    Laptop(int id) {
        //call the superclass id constructor
        super(id);
    }

    /**
     * Copy constructor for the Laptop class.
     * @param copyLaptop The laptop to be copied.
     */
    Laptop(Laptop copyLaptop) {
        super(copyLaptop);
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the laptop is late.
     * @param lateDays The number of days the laptop is late.
     * @return The amount of fees that result because the laptop is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the laptop is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        if (lateDays > 0) {
            return (5 * Math.ceil(lateDays)) + (0.20 * getRentalCost());
        }
        return 0.0;
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Laptop clone() {
        return new Laptop(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Laptop object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Laptop laptop = (Laptop) obj;
        return (this == laptop);
    }
}
