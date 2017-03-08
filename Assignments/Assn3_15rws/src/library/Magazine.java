/**
 * Magazine inherits from the Book class, i.e. is a subclass of Book, and represents an magazine in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Magazine extends Book {

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Book constructor that has every attribute.
     * @param name The name of the book.
     * @param authors The authors of the book.
     * @param publisher The publisher of the book.
     * @param year The year of the book.
     */
    Magazine(String name, String[] authors, String publisher, int year) {
        super(name, authors, publisher, year);
    }

    /**
     * Copy constructor for the book class.
     * @param copyMagazine The book to be copied.
     */
    Magazine(Magazine copyMagazine) {
        super(copyMagazine);
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
        return 0.75*Math.ceil(lateDays);
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Magazine clone() {
        return new Magazine(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Magazine object.
     */
    @Override
    public String toString() {
        return super.toString()+" [Magazine]";
    }

    /**
     * Overrides default equals method in Object class.
     * @param obj The object to compare against current instance.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Magazine magazine = (Magazine) obj;
        return (this == magazine);
    }
}

