/**
 * Book inherits from the Item class, i.e. is a subclass of Item, and represents a book in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

//part of the library package
package library;

//class definition
public class Book extends Item {

    private String[] authors;
    private String publisher;
    private int year;

    /**
     * Calculates the late fees based off of how many days the book is late.
     * @param lateDays The number of days the book is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    public double getLateFees(double lateDays) {
        //calculate and return the late fee, using the ceil function on lateDays
        return 0.5*Math.ceil(lateDays);
    }


}
