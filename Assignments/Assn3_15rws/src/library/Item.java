/**
 * Item is an abstract class that provides the underlining structure for all other objects in the library system.
 *
 * @author Robert Saunders (NetID: 15rws, Student #: 10194030)
 * @version 1.0.0
 */

package library;

public abstract class Item {
    private int Id;
    private String name;

    //abstract method, required in all classes that extend item
    //implementation not needed in the item class
    public abstract int getLateFees(int lateDays);

    //Copy constructor
    public Item(Item copyItem) {
        if (copyItem == null) {
            System.out.print("Passing null object to copy, fatal error.");
            System.exit(0);
        }
        ID = copyItem.ID;
        name = copyItem.name;
    }

    //general constructor
    public Item() {

    }



}
