package library;

/**
 * Created by robertsaunders on 2017-02-25.
 */
public abstract class Item {
    private int ID;
    private String name;

    //abstrace method, required in all classes that extend item
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

    public Item() {

    }



}
