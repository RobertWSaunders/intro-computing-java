/**
 * Created by robertsaunders on 2017-04-22.
 */

public class ClassSea extends ClassBee {
    public String methodOne(String one, String two, String three) {
        return one + two + three;
    }
    public String methodOne(String one) {
        return "giant " + one;
    }
    public void methodTwo(String one) {
        super.methodTwo(one);
        System.out.println("swallow");
    }
} // end ClassSea
