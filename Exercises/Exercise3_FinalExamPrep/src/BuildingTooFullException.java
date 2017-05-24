/**
 * Created by robertsaunders on 2017-04-22.
 */
public class BuildingTooFullException extends Exception {

    private int capacityNumeber;

    BuildingTooFullException() {
        super("Hello");
    }

    BuildingTooFullException(String message) {
        super(message);
    }

    public int getCapacityNumeber() {
        return capacityNumeber;
    }


}
