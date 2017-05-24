import java.io.*;

/**
 * Created by robertsaunders on 2017-04-22.
 */
public abstract class Building {

    //package access
    String man;

    //Class definitions
    public String address;

    Building() {
        setAddress("Unknown");
    }

    Building(Building copyBuilding) {
        if (copyBuilding == null) {
            System.out.print("Cannot proceed.");
            System.exit(0);
        }
        setAddress(copyBuilding.getAddress());
    }

    //Setters
    public void setAddress(String address) {
        this.address = address;
    }

    //Getters


    public String getAddress() {
        return address;
    }


    public abstract double currentCapacity();

}



