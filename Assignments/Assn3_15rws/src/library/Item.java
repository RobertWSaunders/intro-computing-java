/**
 * Item is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

//part of the library package
package library;

//class definition
public abstract class Item {

    ///////////////////////
    /* CLASS ATTRIBUTES */
    /////////////////////

    //every item in the library has a unique identifier
    private int id;
    //every item in the library has a name
    private String name;

    ///////////////////
    /* CONSTRUCTORS */
    /////////////////

    //define a incrementer for the item id, increments every time an item is created.
    private static int idIncrementer = -1;

    /**
     * Copy constructor, takes a item and copy it to new item.
     * @param copyItem The item that is to be copied.
     */
     Item(Item copyItem) {
        //make sure that the item to copy is not null
        if (copyItem == null) {
            //let the user know the error and tell them where it is in the code
            System.out.print("Passing null object to copy, fatal error. [Item --> Item(Item copyItem)]");
            //exit the program as a result of a fatal error.
            System.exit(0);
        }
        //set the id of the item to unique and increment from idIncrementer variable.
        setId();
        //set the name of the item to that of the copyItem
        setName(copyItem.name);
    }

    /**
     * General constructor that sets each class attribute.
     */
     Item(String name) {
        //set the name of the item
        setName(name);
    }

    /**
     * Default constructor for an item.
     */
    Item() {
         setName("Unknown");
         setId();
    }

    //////////////
    /* SETTERS */
    ////////////

    /**
     * Sets the name of the item.
     * @param name The name of the item to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the id of the item by incrementing the idIncrementer variable.
     */
    public void setId() {
        this.id = idIncrementer++;
    }

    //////////////
    /* GETTERS */
    ////////////

    /**
     * Gets the id of the item.
     * @return The id as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the item.
     * @return The name as a string.
     */
    public String getName() {
        return name;
    }

    ///////////////////////
    /* ABSTRACT METHODS */
    /////////////////////

    /**
     * Calculates the late fees based off of how many days the item is late.
     * Implementation differs based on the type of item, therefore abstract method.
     * @param lateDays The number of days the item is late.
     * @return The amount of fees that result because the item is late.
     */
    public abstract double getLateFees(double lateDays);

}
