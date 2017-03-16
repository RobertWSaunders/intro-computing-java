/**
 * Device inherits from the Item class, i.e. is a subclass of Item, and represents a device in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Device extends Item {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //define the rental cost for a device
    private double rentalCost;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor of a device.
     */
    Device() {
        //call the superclass constructor
        super();
        //set the default rental cost be $10
        setRentalCost(10.0);
    }

    /**
     * Device constructor that has every attribute.
     * @param name The name of the device.
     * @param rentalCost The rental cost of the device.
     */
    Device(String name, double rentalCost) {
        //call superclass constructor
        super(name);
        //set any extra class attributes not set by the superclass
        //set the rental cost of the device
        setRentalCost(rentalCost);
    }

    /**
     * Copy constructor for the device class.
     * @param copyDevice The device to be copied.
     */
    Device(Device copyDevice) {
        //call the super copy constructor
        super(copyDevice);
        //set the rental cost to be the same as the copy devices
        setRentalCost(copyDevice.getRentalCost());
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the name of the device.
     * @param cost The rental cost of the device to be set.
     */
    public void setRentalCost(double cost) {
        this.rentalCost = cost;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the rental cost of a device.
     * @return The rental cost as an double.
     */
    public double getRentalCost() {
        return rentalCost;
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the device is late.
     * @param lateDays The number of days the device is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        //if there are any late days return
        if (lateDays > 0) {
            return (2*Math.ceil(lateDays))+(0.10*getRentalCost());
        }
        //otherwise only return 0.0
        return 0.0;
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Device clone() {
        return new Device(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Device object.
     */
    @Override
    public String toString() {
        //adds to the implementation in Item class
        return super.toString()+"\n"+getClass().getSimpleName()+" Rental Cost: "+rentalCost;
    }

    /**
     * Overrides default equals method in Object class, currently same implementation as Item, not needed but good for flexibility.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
