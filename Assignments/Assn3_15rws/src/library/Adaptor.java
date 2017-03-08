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
        //calculate and return the late fee, using the ceil function on lateDays
        return (2.5*Math.ceil(lateDays))+(0.15*getRentalCost());
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
        Adaptor adaptor = (Adaptor) obj;
        return (this == adaptor);
    }
}
