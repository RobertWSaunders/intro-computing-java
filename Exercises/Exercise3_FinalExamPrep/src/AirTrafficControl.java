/**
 * Created by robertsaunders on 2017-04-22.
 */
public class AirTrafficControl {

    public static int lambdaFunction(LambdaInterface l, int x) {
        return l.perform(x);
    }

    public static void main(String[] args) {
        Airport myAirport = new Airport();

        LambdaInterface myLamdba = (x) -> x;
        lambdaFunction(myLamdba,9);


        lambdaFunction(new LambdaInterface() {
            @Override
            public int perform(int x) {
                return x;
            }
        },9);

        myLamdbaWithNine my = new myLamdbaWithNine();
        my.perform(8);


    }
}

@FunctionalInterface
interface LambdaInterface {
    int perform(int x);
}

class myLamdbaWithNine implements LambdaInterface {
    @Override
    public int perform(int x) {
        return x;
    }
}