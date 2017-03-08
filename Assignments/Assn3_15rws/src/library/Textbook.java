/**
 * Textbook inherits from the Book class, i.e. is a subclass of Book, and represents an textbook in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */
package library;

public class Textbook extends Book {

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Textbook constructor that has every attribute.
     * @param name The name of the textbook.
     * @param authors The authors of the textbook.
     * @param publisher The publisher of the textbook.
     * @param year The year of the textbook.
     */
    Textbook(String name, String[] authors, String publisher, int year) {
        super(name, authors, publisher, year);
    }

    /**
     * Copy constructor for the Textbook class.
     * @param copyTextbook The textbook to be copied.
     */
    Textbook(Textbook copyTextbook) {
        super(copyTextbook);
    }

    ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the textbook is late.
     * @param lateDays The number of days the textbook is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return 1*Math.ceil(lateDays);
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Textbook clone() {
        return new Textbook(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Textbook object.
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
        Textbook textbook = (Textbook) obj;
        return (this == textbook);
    }
}
