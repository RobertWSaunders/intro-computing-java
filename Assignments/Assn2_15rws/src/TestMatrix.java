/**
 * Created by robertsaunders on 2017-02-12.
 */

public class TestMatrix {
    public static void main(String[] args) {
       /* Matrix m = new Matrix(5,6);
        System.out.printf("%d",m.getN());
        System.out.printf("%d",m.getM());




       // Matrix me = new Matrix("./matrix.csv");
       // Matrix bob = me.add(test);
       // System.out.print(test.get(0,0));

        Matrix test = new Matrix();
        */
        Matrix test = new Matrix("./matrix.csv");
        test.print("./output.csv");
    }
}
