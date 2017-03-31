/**
 * Adapter inherits from the Device class, i.e. is a subclass of Device, and represents an adapter in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Adaptor extends Device {

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor for adaptor class.
     * Not needed as just calling super but here for consistency.
     */
    Adaptor() {
        super();
    }

    /**
     * Constructor that takes an id.
     * @param id The id to be set as the adaptor id.
     */
    Adaptor(int id, String name) {
        //call the superclass id constructor
        super(id, name);
    }

    /**
     * Adaptor constructor that has every attribute.
     * @param name The name of the adaptor.
     * @param rentalCost The rental cost of the adaptor.
     */
    Adaptor(String name, double rentalCost) {
        //call the superclass constructor
        super(name, rentalCost);
    }

    /**
     * Copy constructor for the Adaptor class.
     * @param copyAdaptor The adaptor to be copied.
     */
    Adaptor(Adaptor copyAdaptor) {
        super(copyAdaptor);
    }


    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the adapter is late.
     * @param lateDays The number of days the adapter is late.
     * @return The amount of fees that result because the adapter is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the adapter is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //if there are more than zero late days
        if (lateDays > 0) {
            //calculate and return the late fee, using the ceil function on lateDays
            return (2.5*Math.ceil(lateDays))+(0.15*getRentalCost());
        }
        //return zero otherwise
        return 0.0;
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Adaptor clone() {
        return new Adaptor(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Adapter object.
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
        return super.equals(obj);
    }
}
