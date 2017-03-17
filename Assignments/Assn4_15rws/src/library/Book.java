/**
 * Book inherits from the Item class, i.e. is a subclass of Item, and represents a book in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public class Book extends Item {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //a book can have authors, one or many
    private String[] authors;
    //a book has a publisher
    private String publisher;
    //a book has a year
    private int year;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    /**
     * Default constructor for the book class.
     */
    Book() {
        //set authors to an empty array
        setAuthors(new String[1]);
        //set publisher to an unknown value
        setPublisher("Unknown");
        //set the year to be current year
        setYear(2017);
    }

    /**
     * Book constructor that has every attribute.
     * @param name The name of the book.
     * @param authors The authors of the book.
     * @param publisher The publisher of the book.
     * @param year The year of the book.
     */
    Book(String name, String[] authors, String publisher, int year) {
        //call superclass constructor
        super(name);
        //set any extra class attributes not set by the superclass
        //set the authors of the book
        setAuthors(authors);
        //set the publisher of the book
        setPublisher(publisher);
        //set the year of the book
        setYear(year);
    }

    /**
     * Constructor that takes an id.
     * @param id The id to be set as the book id.
     */
    Book(int id) {

    }

    /**
     * Copy constructor for the book class.
     * @param copyBook The book to be copied.
     */
    Book(Book copyBook) {
        super(copyBook);
        setYear(copyBook.getYear());
        setPublisher(copyBook.getPublisher());
        setAuthors(copyBook.getAuthors());
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the authors of the book.
     * @param authors The authors of the book to be set.
     */
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    /**
     * Sets the publisher of the book.
     * @param publisher The publisher of the book to be set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Sets the year of the book.
     * @param year The year of the book to be set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the authors of the book.
     * @return The authors as an array of strings.
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * Gets the publisher of the book.
     * @return The publisher of the book as a string.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Gets the year of the book.
     * @return The the year of the book as an integer.
     */
    public int getYear() {
        return year;
    }

     ////////////////
    /* OVERRIDES */
    //////////////

    /**
     * Calculates the late fees based off of how many days the book is late.
     * @param lateDays The number of days the book is late.
     * @return The amount of fees that result because the item is late.
     * NOTE: Implementation includes Math.ceil to indicate that if the item is brought back the fee is rounded to the next day.
     */
    @Override
    public double getLateFees(double lateDays) {
        //no need to check if late days is zero because multiplying
        //calculate and return the late fee, using the ceil function on lateDays
        return 0.5*Math.ceil(lateDays);
    }

    /**
     * Overrides default clone method in Object class.
     * @return Returns a clone of current instance object.
     * NOTE: Using copy constructor to create a clone.
     */
    @Override
    public Book clone() {
        return new Book(this);
    }

    /**
     * Overrides default toString method in Object class.
     * @return A string to represent the Book object.
     */
    @Override
    public String toString() {
        return super.toString()+"\n"+getClass().getSimpleName()+" Publisher: "+publisher+"\n"+getClass().getSimpleName()+" Year: "+year;
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
