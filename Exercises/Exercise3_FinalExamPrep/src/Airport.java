import java.util.ArrayList;

/**
 * Created by robertsaunders on 2017-04-22.
 */
public class Airport extends Building {

    public int runways;
    public ArrayList<Airplane> airplanes;
    public ArrayList<Person> people;

    @Override
    public double currentCapacity() {
        try {
            throw new BuildingTooFullException("Yo, it's too full");
        }
        catch (BuildingTooFullException e){
            String string  = e.getMessage();
        }

    }

}
